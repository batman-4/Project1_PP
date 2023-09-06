package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    //function create(add) and read(find) words
    // listAll
    Scanner s;
    ArrayList<Word> list;
    final String fname = "Dictionary.txt";
    WordCRUD(Scanner s) {
        list = new ArrayList<>();
        this.s = s;
    }

    @Override
    public Object add() {
        System.out.println("난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.next();
        s.nextLine();
        System.out.println("뜻 입력 : ");
        String meaning = s.nextLine();

        return new Word(0, level, word, meaning);
    }

    public void addWord() {
        Word one = (Word)add();
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다 !!!\n");
    }

    public ArrayList<Integer> listAll(String keyword) {
        ArrayList<Integer> idList = new ArrayList<>();
        System.out.println("--------------------------------");
        int j = 1;
        for(int i = 0; i<list.size(); i++) {
            if(!(list.get(i).getWord()).contains(keyword)) continue;
            System.out.print(j + " ");
            System.out.println(list.get(i).toString());
            idList.add(i);
            j++;
        }
        System.out.println("--------------------------------\n");
        return idList;
    }

    @Override
    public int update(Object obj) {

        return 0;
    }

    public void updateItem() {
        System.out.println("수정할 단어 검색 : ");
        String keyword = s.next();
        s.nextLine();

        ArrayList<Integer> idList = this.listAll(keyword);

        System.out.println("수정할 번호 검색 : ");
        int id = s.nextInt();
        Word word = list.get(idList.get(id-1));
        System.out.println("뜻 입력 : ");
        s.nextLine();
        String newMeaning = s.nextLine();
        word.setMeaning((newMeaning));
        System.out.println("단어가 수정되었습니다.\n");
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    public void listAll() {
        System.out.println("--------------------------------");
        for(int i = 0; i<list.size(); i++) {
            System.out.print(i+1 + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------\n");
    }

    public void deleteItem() {
        System.out.println("삭제할 단어 검색 : ");
        String keyword = s.next();
        s.nextLine();

        ArrayList<Integer> idList = this.listAll(keyword);

        System.out.println("삭제할 번호 검색 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.println("정말로 삭제하실래요? (Y/n) : ");
        if((s.nextLine()).equalsIgnoreCase("y")) {
            list.remove(idList.get(id-1));
            System.out.println("단어가 삭제되었습니다.\n");
        }
        else System.out.println("취소되었습니다.\n");
    }

    public void loadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;
            int count = 0;

            while(true) {
                line = br.readLine();
                if(line == null) break;
                String data[] = line.split("\\|");

                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count++;
            }
            br.close();
            System.out.println("==> " + count + "개 로딩 완료!!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter(fname));
            for(Word one : list) {
                pr.write(one.toFileString() + "\n");
            }
            pr.close();
            System.out.println("데이타 저장 완료!!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listAll (int level) {
        System.out.println("--------------------------------");
        int count = 1;

        for(int i=0; i<list.size(); i++) {
            if(list.get(i).getLevel() == level) {
                System.out.println(count + " " + list.get(i).toString());
                count++;
            }
        }
        System.out.println("--------------------------------\n");
    }

    public void searchLevel() {
        System.out.println("==> 원하는 레벨은? (1-3) ");
        int level = s.nextInt();
        s.nextLine();

        listAll(level);
        System.out.println();
    }

    public void searchWord() {
        System.out.println("==> 원하는 단어는? ");
        String keyword = s.next();
        s.nextLine();

        listAll(keyword);
        System.out.println();
    }
}
