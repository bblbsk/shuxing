package com.shuxing.serializable.kryo.model;



public class MachineDTO {

	private String department;

	private Integer cpu;

	private String cpuLoad;

	private String differCharge;

	private String differPercent;

	private int disk;

	private int groupId;

	public Integer getCpu() {
		return this.cpu;
	}

	public void setCpu(Integer cpu) {
		this.cpu = cpu;
	}

	public String getCpuLoad() {
		return this.cpuLoad;
	}

	public void setCpuLoad(String cpuLoad) {
		this.cpuLoad = cpuLoad;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDifferCharge() {
		return this.differCharge;
	}

	public void setDifferCharge(String differCharge) {
		this.differCharge = differCharge;
	}

	public String getDifferPercent() {
		return this.differPercent;
	}

	public void setDifferPercent(String differPercent) {
		this.differPercent = differPercent;
	}

	public int getDisk() {
		return this.disk;
	}

	public void setDisk(int disk) {
		this.disk = disk;
	}

	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	public static MachineDTO getMachineDTO() {
		MachineDTO dto = new MachineDTO();
		dto.setCpu(4);
		dto.setCpuLoad("2.2");
		dto.setDepartment("技术中心");
		dto.setDifferCharge("333");
		dto.setDifferPercent("0.4");
		dto.setDisk(500);
		dto.setGroupId(111);
		return dto;
	}

	@Override
	public String toString() {
		return "MachineDTO [department=" + department + ", cpu=" + cpu
				+ ", cpuLoad=" + cpuLoad + ", differCharge=" + differCharge
				+ ", differPercent=" + differPercent + ", disk=" + disk
				+ ", groupId=" + groupId + "]";
	}
	

}