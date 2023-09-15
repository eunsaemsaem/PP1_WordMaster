package org.example;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD { //파일 작성 > 파일 로드 > 수정, 삭제

    ArrayList<Word> list;
    Scanner s;
    File fname = new File ("C:\\Users\\sweee\\Desktop\\pp_wordFile.txt"); //프로젝트 안으로 경로 옮기기 !


    WordCRUD(Scanner s) {
        list = new ArrayList<>();
        this.s = s;
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

        ArrayList<Integer> keylist = new ArrayList<>(); //새로운 list 생성
        int j = 1;

        System.out.println("------------------------");

        for (int i = 0; i < list.size(); i++) {
            String word = list.get(i).getWord();

            if (word.contains(keyword)) {
                System.out.print (j + " ");
                System.out.println (list.get(i).toString());

                keylist.add(i);
                j++;
            }
        }
        System.out.println("------------------------");
        return keylist;
    }

    public ArrayList<Integer> listAll(int levelNum) {

        ArrayList<java.lang.Integer> levellist = new ArrayList<>(); //새로운 list 생성
        int j = 1;

        System.out.println("------------------------");

        for (int i = 0; i < list.size(); i++) {
            int levelN = list.get(i).getLevel();

            if (levelN != levelNum) { //단어가 keyword를 포함하지 않으면
                continue;
            }

            System.out.print(j + " ");
            System.out.println(list.get(i).toString());

            j++;
        }
        System.out.println("------------------------");
        return levellist;
    }


    @Override
    public Object add() {
        System.out.print ("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();

        System.out.print ("=> 뜻 입력 : ");
        String meaning = s.nextLine();

        return new Word(0, level, word, meaning);
    }

    public void addItem() {

        Word one = (Word) add();
        list.add(one);

        System.out.println("새 단어가 단어장에 추가되었습니다.");

    }

    @Override
    public int update(Object obj) {
    }

    public void updateItem() {

        System.out.print ("=> 수정할 단어 검색 : ");
        String keyword = s.next();

        ArrayList<Integer> idlist = this.listAll(keyword);

        System.out.print ("=> 수정할 번호 선택 : ");
        int id = s.nextInt();
        //if id가 범위에 없는 값이라면? -> 에러 안내
        s.nextLine();

        System.out.print ("=> 뜻 입력 : ");
        String meaning = s.nextLine();

        Word word = list.get(idlist.get(id-1));
        word.setMeaning(meaning);

        System.out.println ("단어가 수정되었습니다. ");
    }

    @Override
    public int delete(Object obj) {
    }

    public void deleteItem() {

        System.out.print("=> 삭제할 단어 검색 : ");
        String keyword = s.next();

        ArrayList<Integer> idlist = this.listAll(keyword);

        System.out.print("=> 삭제할 번호 선택 : ");
        int num = s.nextInt();
        s.nextLine(); //enter

        System.out.print("=> 정말로 삭제하시겠습니까? (Y/N) : ");
        String ans = s.nextLine();

        if (ans.equals("Y") || ans.equals("y")) {
            list.remove((int) idlist.get(num - 1));
            System.out.println("단어가 삭제되었습니다.");
        } else {
            System.out.println("취소되었습니다.");
        }

    }

    @Override
    public void selectOne(int id) {
    }

    public void searchLevel() {
        System.out.println("=> 원하는 레벨은? (1~3) : ");

        int level = s.nextInt();
        listAll(level);
    }

    public void searchWord() {
        System.out.println("=> 원하는 단어는? : ");

        String keyword = s.nextLine();
        s.nextLine();
        listAll(keyword);
    }


    public void loadFile() {
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

            System.out.println("==> " + count + "개 로딩 완료");
        } catch (IOException e) { //파일 읽기 중 에러 발생
            e.printStackTrace();
        } catch (NullPointerException e) { //null이 있을 경우
            e.printStackTrace();
        }
    }//loadFile

    public void saveFile() {

        try {
            PrintWriter pr = new PrintWriter(new FileWriter(fname));

            for (Word one : list) {
                pr.write (one.toFileString() + "\n");
            }

            pr.close();
            System.out.println ("==> 데이터 저장 완료 !!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }//saveFile


}
