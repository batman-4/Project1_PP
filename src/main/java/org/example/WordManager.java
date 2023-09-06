package org.example;

import java.util.Scanner;

public class WordManager {
    //create selectMenu function
    Scanner s = new Scanner(System.in);
    WordCRUD wordCRUD;
    WordManager(){
        wordCRUD = new WordCRUD(s);
    }

    public int selectMenu() {
        System.out.println("*** 영단어 마스터 ***\n" +
                "********************\n" +
                "1. 모든 단어 보기\n" +
                "2. 수준별 단어 보기\n" +
                "3. 단어 검색\n" +
                "4. 단어 추가\n" +
                "5. 단어 수정\n" +
                "6. 단어 삭제\n" +
                "7. 파일 저장\n" +
                "0. 나가기\n" +
                "********************");
        System.out.println("원하는 메뉴는?");
        return s.nextInt();
    }

    public void start() {
        wordCRUD.loadFile();
        while(true) {
            int choice = selectMenu();
            if(choice == 0) {
                //System.out.println("Program will be ended.");
                break;
            }
            if(choice == 1) {
                wordCRUD.listAll();
            }
            else if(choice == 2) {
                wordCRUD.searchLevel();
            }
            else if(choice == 3) {
                wordCRUD.searchWord();
            }
            else if (choice == 4) {
                wordCRUD.addWord();
            }
            else if(choice == 5) {
                wordCRUD.updateItem();
            }
            else if(choice == 6) {
                wordCRUD.deleteItem();
            }
            else if(choice == 7) {
                wordCRUD.saveFile();
            }
        }
    }
}
