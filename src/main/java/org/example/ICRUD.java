package org.example;

public interface ICRUD {

    public Object add(); //데이터 추가 시 사용
                         // 해당 추가된 객체를 return하기 위해 Object 사용
    public int update (Object obj); //데이터 수정
    public int delete (Object obj); //데이터 삭제
    public void selectOne (int id); //데이터 1개 조회

}
