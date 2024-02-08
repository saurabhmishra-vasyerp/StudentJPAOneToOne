package com.example.StudentLib.Dto;

public class ResponseDTO {
	
	private int id;
	private String name;
	private Object data;

	@Override
	public String toString() {
		return "ResponseDTO [id=" + id + ", name=" + name + ", data=" + data + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ResponseDTO(int id, String name, Object data) {
		super();
		this.id = id;
		this.name = name;
		this.data = data;
	}

}
