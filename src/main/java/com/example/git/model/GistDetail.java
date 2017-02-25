package com.example.git.model;

import java.util.ArrayList;
import java.util.List;

public class GistDetail {
	
	private GistModel gistModel;
	private List<GistModel> list = new ArrayList<>();
	/**
	 * @return the gistModel
	 */
	public GistModel getGistModel() {
		return gistModel;
	}
	/**
	 * @param gistModel the gistModel to set
	 */
	public void setGistModel(GistModel gistModel) {
		this.gistModel = gistModel;
	}
	/**
	 * @return the list
	 */
	public List<GistModel> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<GistModel> list) {
		this.list = list;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GistDetail [gistModel=" + gistModel + ", list=" + list + "]";
	}
}
