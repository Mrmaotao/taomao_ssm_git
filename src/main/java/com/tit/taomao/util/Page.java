package com.tit.taomao.util;

public class Page {
	int start;//当前页数的首条记录的下标
	int count;//一页中有几条数据
	int total;//数据总数
	String param;//其他参数
	/*
	 * 分页的默认值
	 */
	public static final int defaultCount=5;
	public Page(){
		count = defaultCount;
	}
	public Page(int start, int count){
		this.start = start;
		this.count = count;
	}
	public int getTotalPage(){
		int pagecount=total/count;	
		if(total%count>0){
			pagecount++;
		}
		return pagecount;
	}
	
	/*
	 * 获取最后一页的首条记录的下标
	 */
	public int getLastPageone(){
		int lastone = total%count ;
		if(lastone==0)
		{
			lastone=total-count;
		}else{
			lastone = total-lastone;
		}
		return lastone;
	}
    /*
     * 判断是否还有上一页
     */
	public boolean isFront(){
		if(start==0)
            return false;
		return true;
	}
	/*
	 * 判断是否还有下一页
	 */
	public boolean isLast(){
		if(start==getLastPageone())
			return false;
		return true;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	@Override
	public String toString() {
		return "Page [start=" + start + ", count=" + count + ", total=" + total + ", getTotalPage()=" + getTotalPage()
				+ ", getLastPageone()=" + getLastPageone() + ", isFront()=" + isFront() + ", isLast()=" + isLast()
				+ ", getStart()=" + getStart() + ", getCount()=" + getCount() + ", getTotal()=" + getTotal() + "]";
	}
	

}
	
