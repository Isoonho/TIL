package soloTest;

import java.util.*;


public class BroadCastStation {


    Scanner scanner = new Scanner(System.in);
    Program[] programs;
    int currentCount;

    BroadCastStation() {
        currentCount = 0;
        programs = new Program[100];
    }

    public void BroadCastMenu() {
        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("1.추가  |  2.삭제|  3.검색|  4.출력|  5.수정|  6.종료");
            System.out.println("--------------------------------------------------");


            System.out.print("선택 하세요: ");
            String selectNo = scanner.nextLine();
            switch (selectNo) {
                case "1":
                    addProgram();
                    break;
                case "2":
                    deleteProgram();
                    break;
                case "3":
                    searchProgram();
                    break;
                case "4":
                    outPutProgram();
                    break;
                case "5":
                    adjustProgram();
                    break;
                case "6":
                    return;
            }
        }
    }


    public void deleteProgram() {
        System.out.println("삭제하실 프로그램을 입력하세요: ");
        String str = scanner.nextLine();
        for (int i = 0; i < currentCount; i++) {
            if (str.equals(programs[i].getName())) {
                for (int k = i; k < currentCount - 1; k++) {
                    programs[k] = programs[k + 1];
                    programs[k + 1] = null;

                }
                currentCount--;
                break;
            }
        }
    }


    public void adjustProgram() {
        Program adjustProgram = null;

        System.out.println("수정하실 프로그램을 입력하세요: ");
        String str = scanner.nextLine();
        for (int i = 0; i < currentCount; i++) {
            if (str.equals(programs[i].getName())) {
                printProgram(programs[i]);
                adjustProgram = programs[i];
                break;
            }
        }

        if (adjustProgram != null) {
            System.out.println("----------------------------------------------------------------");
            System.out.println("1.프로그램 이름  |  2.방송하는 요일|  3.pd이름|  4.방송하는 시간|  5.종료");
            System.out.println("----------------------------------------------------------------");


            System.out.print("수정하신 항목을 선택하세요: ");
            String selectNo = scanner.nextLine();

            System.out.println("수정하실 내용을 입력하세요: ");
            String mainStory = scanner.nextLine();

            switch (selectNo) {
                case "1":
                    adjustProgram.setName(mainStory);
                    break;
                case "2":
                    adjustProgram.setDayOfTheWeek(mainStory);
                    break;
                case "3":
                    adjustProgram.setPdName(mainStory);
                    break;
                case "4":
                    adjustProgram.setTime(mainStory);
                    break;
                case "5":
                    return;
            }
            printProgram(adjustProgram);
        } else {
            System.out.println("검색하신 프로그램이 존재하지 않습니다.");
        }
    }

    public void addProgram() {
        // TODO name, ~ 요소들을 받아야함
        String name;
        String dayOfTheWeek;
        String pdName;
        String time;

        System.out.print("프로그램 이름: ");
        name = scanner.nextLine();

        System.out.print("방송하는 요일: ");
        dayOfTheWeek = scanner.nextLine();

        System.out.print("pd 이름: ");
        pdName = scanner.nextLine();

        System.out.print("방송하는 시간: ");
        time = scanner.nextLine();

        Program broadcast = new Program(name, dayOfTheWeek, pdName, time);

        programs[currentCount] = broadcast;
        currentCount++;
    }


    public void outPutProgram() {
        for (Program b : programs) {
            if (b != null) {
                printProgram(b);
            } else {
                break;
            }
        }
    }

    public void searchProgram() {
        System.out.print("검색하실 프로그램 이름을 입력하세요: ");
        String str = scanner.nextLine();
        for (int i = 0; i < currentCount; i++) {
            if (str.equals(programs[i].getName())) {
                printProgram(programs[i]);
            }
        }
    }

    public void printProgram(Program program) {
        System.out.println("프로그램 이름 : " + program.getName());
        System.out.println("방송하는 요일 : " + program.getDayOfTheWeek());
        System.out.println("PD 이름 : " + program.getPdName());
        System.out.println("방송하는 시간 : " + program.getTime());
        System.out.println();
    }

}




