package cn.edu.zzti.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.edu.zzti.dto.FigureRecordSearchBean;
import cn.edu.zzti.dto.Page;
import cn.edu.zzti.entity.FigureRecord;
import cn.edu.zzti.entity.Kid;
import cn.edu.zzti.service.FigureRecordService;
import cn.edu.zzti.service.KidService;
import cn.edu.zzti.util.LoginNameUtils;

/**
 * 成长身影记录控制类
 * 
 * @author nowto
 * 
 */
@Controller
@RequestMapping("/figurerecord")
public class FigureRecordController {
	private int BUFFER_LENGTH = 1024;
	@Resource
	private KidService kidService;
	@Resource
	private FigureRecordService figureRecordService;

	@ModelAttribute("kids")
	List<Kid> getKids(HttpSession session) {
		String username = LoginNameUtils.getLoginUsername(session);
		List<Kid> kids = kidService.get(username);
		return kids;
	}

	/**
	 * 到记录页 可搜索
	 * 
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/more", method = RequestMethod.GET)
	public String getMoreFigureRecord(@ModelAttribute("kids") List<Kid> kids,
			FigureRecordSearchBean figurerecordSB, Model model,
			HttpSession session) {
		figurerecordSB.setPageSize(21);
		String username = LoginNameUtils.getLoginUsername(session);
		Page<FigureRecord> page = figureRecordService.search(figurerecordSB,
				username);
		model.addAttribute("page", page);
		model.addAttribute("sb", figurerecordSB);
		return "/figurerecord/more";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAdd(@ModelAttribute("kids") List<Kid> kids) {
		return "/figurerecord/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String postAdd(@RequestParam("file") CommonsMultipartFile file,
			FigureRecord figureRecord, Model model,
			javax.servlet.http.HttpServletRequest request, HttpSession session)
			throws IOException {
		String realFileName = UUID.randomUUID().toString();
		figureRecord.setRealFileName(realFileName);
		figureRecord.setSourceFileName(file.getOriginalFilename());

		String path = request.getRealPath("/WEB-INF/fileupload");
		int type = figureRecord.getType();
		String username = LoginNameUtils.getLoginUsername(session);
		if (figureRecordService.add(figureRecord, username)) {
			InputStream in = file.getInputStream();
			OutputStream out = new FileOutputStream(
					new File(path, realFileName));

			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			in.close();
			out.close();

			model.addAttribute("type", type);
			model.addAttribute("result", "添加成功");
			return "redirect:/figurerecord/more.do";
		} else {
			model.addAttribute("result", "添加失败");
			return "/figurerecord/add";
		}
	}

	@RequestMapping("s_v")
	public String s_v(Integer id, Model model) {
		FigureRecord figureRecord = figureRecordService.get(id);
		model.addAttribute("figurerecord", figureRecord);
		return "/figurerecord/s_v";
	}

	@RequestMapping("/remove")
	public void remove(String ids){
		String[] strs = ids.split(",");
		List<Integer> list = new ArrayList<>();
		for (String str : strs) {
			list.add(Integer.parseInt(str));
		}
		figureRecordService.removeAll(list);
	}
	
	@RequestMapping("s")
	public void s(Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			FigureRecord figureRecord = figureRecordService.get(id);
			String path = request.getRealPath("/WEB-INF/fileupload");
			// File s = new File(path, figureRecord.getRealFileName());
			// InputStream in = new FileInputStream(s);
			// OutputStream out = response.getOutputStream();
			//
			// int len = 0;
			// byte[] buffer = new byte[1024];
			// while ((len = in.read(buffer)) != -1) {
			// out.write(buffer, 0, len);
			// }
			// in.close();
			// String videoFilename =
			// URLDecoder.decode(request.getParameter("video"), "UTF-8");
			Path video = Paths.get(path, figureRecord.getRealFileName());

			int length = (int) Files.size(video);
			int start = 0;
			int end = length - 1;

			String range = request.getHeader("Range");
			// Matcher matcher = RANGE_PATTERN.matcher(range);
			//
			// if (matcher.matches()) {
			// String startGroup = matcher.group("start");
			// start = startGroup.isEmpty() ? start :
			// Integer.valueOf(startGroup);
			// start = start < 0 ? 0 : start;
			//
			// String endGroup = matcher.group("end");
			// end = endGroup.isEmpty() ? end : Integer.valueOf(endGroup);
			// end = end > length - 1 ? length - 1 : end;
			// }

			int contentLength = end - start + 1;
			response.reset();
			response.setBufferSize(BUFFER_LENGTH);
			response.setHeader(
					"Content-Disposition",
					String.format("inline;filename=\"%s\"",
							figureRecord.getRealFileName()));
			response.setHeader("Accept-Ranges", "bytes");
			// response.setDateHeader("Last-Modified",
			// Files.getLastModifiedTime(video).toMillis());
			// response.setDateHeader("Expires", System.currentTimeMillis() +
			// EXPIRE_TIME);
			// LogUtil.paramAndResultLocal("--processRequest-",
			// "probeContentType-"+Files.probeContentType(video), "");
			response.setContentType(Files.probeContentType(video));
			response.setHeader("Content-Range",
					String.format("bytes %s-%s/%s", start, end, length));
			response.setHeader("Content-Length",
					String.format("%s", contentLength));
			response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

			int bytesRead;
			int bytesLeft = contentLength;
			ByteBuffer buffer = ByteBuffer.allocate(BUFFER_LENGTH);

			try (SeekableByteChannel input = Files.newByteChannel(video);
					OutputStream output = response.getOutputStream()) {

				input.position(start);

				while ((bytesRead = input.read(buffer)) != -1 && bytesLeft > 0) {
					buffer.clear();
					output.write(buffer.array(), 0,
							bytesLeft < bytesRead ? bytesLeft : bytesRead);
					bytesLeft -= bytesRead;
				}
			}
		} catch (Exception e) {
		}
	}
}
