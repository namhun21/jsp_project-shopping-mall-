package util;

public class Pagination {
	private int curPage; // 현재 페이지
	private int totalPage; // 전체 페이지 개수
	private int totalContentCnt; // 전체 게시물 개수
	private int contentCnt = 9; // 한페이지에 표시할 게시물 개수
	private int pageCnt = 3; // 보여줄 페이지 개수
	private boolean prev; // 이전 페이지 버튼
	private boolean next; // 다음 페이지 버튼
	private int curBlock; // 현재 페이지 블록
	private int lastBlock; // 마지막 페이지 블록
	private int startPage; // 현재 페이지 블록의 시작 페이지
	private int endPage; // 현재 페이지 블록의 마지막 페이지

	private static Pagination singleton;

	private Pagination() {
	}

	public static Pagination getInstance() {
		if (singleton == null) {
			singleton = new Pagination();
		}
		return singleton;
	}

	public void pageInfo(int curPage, int contentCnt, int totalContentCnt) {
		this.curPage = curPage;
		this.contentCnt = contentCnt;
		this.totalContentCnt = totalContentCnt;

		// 전체 페이지 개수 구하기
		this.totalPage = (int) Math.ceil(this.totalContentCnt / (double) this.contentCnt);

		// 현재 페이지 블럭 구하기
		this.curBlock = this.curPage / this.pageCnt;
		if (this.curPage % this.pageCnt > 0) {
			this.curBlock++;
		}

		// 마지막 페이지 블록 구하기
		this.lastBlock = this.totalContentCnt / (this.pageCnt * this.contentCnt);
		if (this.totalContentCnt % (this.pageCnt * this.contentCnt) > 0) {
			this.lastBlock++;
		}

		// 현재 페이지 블록의 시작페이지 구하기 (1,6,11)
		this.startPage = (curBlock * this.pageCnt) - (this.pageCnt - 1);

		// 현재 페이지 블록의 끝페이지 구하기(5,10,15)
		if (lastBlock == curBlock) {
			this.endPage = this.totalPage;
		} else {
			this.endPage = this.startPage + (pageCnt - 1);
		}

		// 이전, 다음 버튼 표시 여부
		if (this.curPage > 0 && this.curPage < (this.pageCnt + 1)) {
			setPrev(false);
			setNext(true);
		} else if (lastBlock == curBlock) {
			setPrev(true);
			setNext(false);
		} else {
			setPrev(true);
			setNext(true);
		}

		if (totalPage > 0 && totalPage < (this.pageCnt + 1)) {
			setPrev(false);
			setNext(false);
		}

	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalContentCnt() {
		return totalContentCnt;
	}

	public void setTotalContentCnt(int totalContentCnt) {
		this.totalContentCnt = totalContentCnt;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getContentCnt() {
		return contentCnt;
	}

	public void setContentCnt(int contentCnt) {
		this.contentCnt = contentCnt;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getLastBlock() {
		return lastBlock;
	}

	public void setLastBlock(int lastBlock) {
		this.lastBlock = lastBlock;
	}

}
