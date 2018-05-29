package cn.edu.zzti.dto;

import cn.edu.zzti.entity.Kid;
import cn.edu.zzti.entity.VaccinePlan;

public class VaccineNotify {
	private Kid kid;
	private VaccinePlan vaccinePlan;
	public VaccineNotify() {
	}
	public VaccineNotify(Kid kid, VaccinePlan vaccinePlan) {
		super();
		this.kid = kid;
		this.vaccinePlan = vaccinePlan;
	}
	public Kid getKid() {
		return kid;
	}
	public void setKid(Kid kid) {
		this.kid = kid;
	}
	public VaccinePlan getVaccinePlan() {
		return vaccinePlan;
	}
	public void setVaccinePlan(VaccinePlan vaccinePlan) {
		this.vaccinePlan = vaccinePlan;
	}
	@Override
	public String toString() {
		return "VaccineNotify [kid=" + kid + ", vaccinePlan=" + vaccinePlan
				+ "]";
	}
}
