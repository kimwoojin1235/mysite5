package com.javaex.vo;

public class GalleryVo {
	private int no, userno, filesize;
	private String content, filepath,orgname,savename,name;
	
	public GalleryVo() {
		super();
	}
	public GalleryVo(int no, int userno, int filesize, String content, String filepath, String orgname,
			String savename) {
		super();
		this.no = no;
		this.userno = userno;
		this.filesize = filesize;
		this.content = content;
		this.filepath = filepath;
		this.orgname = orgname;
		this.savename = savename;
	}
	
	public GalleryVo(int no, int userno, int filesize, String content, String filepath, String orgname, String savename,
			String name) {
		super();
		this.no = no;
		this.userno = userno;
		this.filesize = filesize;
		this.content = content;
		this.filepath = filepath;
		this.orgname = orgname;
		this.savename = savename;
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", userno=" + userno + ", filesize=" + filesize + ", content=" + content
				+ ", filepath=" + filepath + ", orgname=" + orgname + ", savename=" + savename + "]";
	}
	
	
}
