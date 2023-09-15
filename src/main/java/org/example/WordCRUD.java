package org.example;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD { //파일 작성 > 파일 로드 > 수정, 삭제

    ArrayList<Word> list;
    Scanner s;

    WordCRUD(Scanner s) {
        list = new ArrayList<>();
        this.s = s;
    }

    @Override
    public Object add() {
        System.out.println("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();

        System.out.println("=> 뜻 입력 : ");
        String meaning = s.nextLine();

        return new Word(0, level, word, meaning);
    }

    public void addItem() {

        Word one = (Word) add();
        list.add(one);

        System.out.println("새 단어가 단어장에 추가되었습니다.");

    }

    public void listAll() {

        System.out.println("------------------------");

        for (int i = 0; i < list.size(); i++) {
            System.out.print((i + 1) + " ");
            System.out.println(list.get(i).toString());
        }

        System.out.println("--------------------------");

    }

    public ArrayList<Integer> listAll(String keyword) {

        ArrayList<Integer> idlist = new ArrayList<>(); //새로운 list 생성

        System.out.println("------------------------");
        for (int i = 0; i < list.size(); i++) {

            int j = 0;
            String word = list.get(i).getWord();

            if (!word.contains(keyword)) { //단어가 keyword를 포함하지 않으면
                continue;
            }

            System.out.print((j + 1) + " ");
            System.out.println(list.get(i).toString());

            idlist.add(i);
            j++;
        }
        System.out.println("------------------------");
        return idlist;
    }

    public ArrayList<Integer> listAll(int levelNum) {

        ArrayList<java.lang.Integer> levellist = new ArrayList<>(); //새로운 list 생성

        System.out.println("------------------------");
        for (int i = 0; i < list.size(); i++) {

            int j = 0;
            int levelN = list.get(i).getLevel();

            if (!(levelN == levelNum)) { //단어가 keyword를 포함하지 않으면
                continue;
            }

            System.out.print((j + 1) + " ");
            System.out.println(list.get(i).toString());

            levellist.add(i);
            j++;
        }
        System.out.println("------------------------");
        return levellist;
    }

    @Override
    public int update(Object obj) {


        return 0;
    }

    public void updateItem() {

        System.out.println("=> 수정할 단어 검색 : ");
        String keyword = s.nextLine();

        ArrayList<Integer> idlist = this.listAll(keyword); //?

        System.out.println("=> 수정할 번호 선택 : ");
        int num = s.nextInt();
        s.nextLine(); //enter

        System.out.println("뜻 입력 : ");
        String meaning = s.nextLine();

        Word word = list.get(idlist.get(num - 1));
        word.setMeaning(meaning);

        System.out.println("단어가 수정되었습니다.");

    }

    @Override
    public int delete(Object obj) {

        return 0;
    }

    public void deleteItem() {

        System.out.println("=> 삭제할 단어 검색 : ");
        String keyword = s.nextLine();

        ArrayList<Integer> idlist = this.listAll(keyword);

        System.out.println("=> 삭제할 번호 선택 : ");
        int num = s.nextInt();
        s.nextLine(); //enter

        System.out.println("=> 정말로 삭제하시겠습니까? (Y/N");
        String ans = s.nextLine();

        if (ans.equals("Y") || ans.equals("y")) {
            list.remove((int) idlist.get(num - 1));
            System.out.println("단어가 삭제되었습니다.");
        } else if (ans.equals("N") || ans.equals("n")) {
            System.out.println("취소되었습니다.");
        }

    }

    @Override
    public void selectOne(int id) {

    }

    public void selectOneItem (){

        System.out.print ("=> 레벨 (1:초급, 2:중급, 3:고급) 선택 : ");
        int levelNum = s.nextInt();

        ArrayList<Integer> levelList = this.listAll(levelNum);
    }

    public void loadFile() {
        File fname = new File ("C:\\Users\\sweee\\Desktop\\pp_wordFile.txt");

        try {
            BufferedReader br = new BufferedReader (new FileReader(fname));
            String line;
            int count = 0;

            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }

                String data[] = line.split(",");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];

                list.add(new Word(count, level, word, meaning));
                count++;
            }
            br.close();

            System.out.println("==>" + count + "개 로딩 완료");
        } catch (IOException e) { //파일 읽기 중 에러 발생
            e.printStackTrace();
        } catch (NullPointerException e) { //null이 있을 경우
            e.printStackTrace();
        }
    }//loadFile

    public void saveFile() {

    }
}
