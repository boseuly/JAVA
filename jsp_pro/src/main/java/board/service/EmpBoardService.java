package board.service;

import board.model.EmpBoardDAO;
import board.model.EmpBoardDTO;
import board.model.EmpBoardStatisDTO;

public class EmpBoardService {

	public int add(EmpBoardDTO data) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		int seq = dao.getNextSeq();
		data.setId(seq);
		
		boolean result = dao.insertData(data);
		
		if(result) {
			dao.commit();
			dao.close();
			return data.getId();
		}
		dao.rollback();
		dao.close();
		return 0;
	}

	public EmpBoardDTO getData(int id) {
		EmpBoardDAO dao = new EmpBoardDAO();
		EmpBoardDTO data = dao.selectData(id);
		dao.close();
		return data;
	}

	public void incViewCnt(EmpBoardDTO data) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		boolean result = false;
		EmpBoardStatisDTO statisData = dao.selectStatis(data); // 방문 기록을 담은 데이터를 가져온다.
		if(statisData == null) { // 만약 방문한 적이 없다면
			result = dao.updateViewCnt(data); // 조회수 증가
			dao.insertStatis(data); // 방문 기록 해주기
		}else { // 방문한 적이 있는 경우
			java.util.Date now = new java.util.Date(); // 현재 날짜 구하기
			long timeDiff = now.getTime() - statisData.getLatestView().getTime(); // 현재 시각 - 이전 방문 시간
			if(timeDiff >= (1000 * 60 * 60 * 24 * 7)) { // 만약 7일이 경과했다면 다른 식 : timeDiff / (1000 * 60 * 60 * 24) >= 7
				result = dao.updateViewCnt(data);		// 그럼 +1 해준다.
				dao.updateStatis(statisData);
				
			}
		
		}
		
		if(result) {
			data.setViewCnt(data.getViewCnt() + 1); // db는 변경이 됐을지 몰라도 자바에서 불러온 데이터는 이전 데이터이기 때문에
			dao.commit();							// 자바에서도 기존의 viewCnt에서 + 1을 해줘야 한다. 안 그럼 증가 X
		}else {
			dao.rollback();
		}
		dao.close();
	}
	// 추천수 올리기
	public void incLike(EmpBoardDTO data) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		boolean result = dao.updateLike(data);
		if(result) {
			data.setViewCnt(data.getLike() + 1); // db는 변경이 됐을지 몰라도 자바에서 불러온 데이터는 이전 데이터이기 때문에
			dao.commit();							// 자바에서도 기존의 like에서 + 1을 해줘야 한다. 안 그럼 증가 X
		}else {
			dao.rollback();
		}
		dao.close();
	}

}
