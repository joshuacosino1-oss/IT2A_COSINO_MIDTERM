package com.scc.ui;

import com.scc.dao.GymMemberDao;
import com.scc.model.GymMember;

import java.util.List;
import java.util.Scanner;

public class GymMenu {
    private final GymMemberDao memberDao = new GymMemberDao();
    private final Scanner scanner = new Scanner(System.in);

    public void startMenu() {
        int choice = 0;

        do {
            System.out.println("--------------------------------------------------");
            System.out.println("          GYM MEMBERSHIP MANAGEMENT               ");
            System.out.println("--------------------------------------------------");
            System.out.println("[1] Add New Member");
            System.out.println("[2] View All Members");
            System.out.println("[3] Exit System");
            System.out.println("--------------------------------------------------");
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } else {
                System.out.println("\nInvalid input. Please enter a number (1-3).\n");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    addNewMemberFlow();
                    break;
                case 2:
                    viewAllMembersFlow();
                    break;
                case 3:
                    System.out.println("\nExiting system... Thank you!");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please select between 1 and 3.\n");
                    break;
            }

        } while (choice != 3);
    }

    private void addNewMemberFlow() {
        System.out.println("\n--- ADD NEW MEMBER ---");
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter Tier (BASIC / PREMIUM / VIP): ");
        String tier = scanner.nextLine().toUpperCase();

        System.out.print("Enter Monthly Fee: ");
        double monthlyFee = 0.0;
        if (scanner.hasNextDouble()) {
            monthlyFee = scanner.nextDouble();
        }
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Status (ACTIVE / INACTIVE): ");
        String status = scanner.nextLine().toUpperCase();

        GymMember newMember = new GymMember(fullName, tier, monthlyFee, status);
        memberDao.addMember(newMember);

        System.out.println("\nSUCCESS: Member record saved to database!\n");
    }

    private void viewAllMembersFlow() {
        List<GymMember> members = memberDao.getAllMembers();
        System.out.println("\n----------------------------------------------------------------------------------");
        System.out.println("                                ALL GYM MEMBERS                                   ");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("+-----+--------------------+-----------+--------------+------------+");
        System.out.println("| ID  | FULL NAME          | TIER      | MONTHLY FEE  | STATUS     |");
        System.out.println("+-----+--------------------+-----------+--------------+------------+");

        if (members.isEmpty()) {
            System.out.println("|                      No records found in the database.                       |");
        } else {
            for (GymMember m : members) {
                System.out.printf("| %-3d | %-18s | %-9s | %-12.2f | %-10s |\n",
                        m.getId(), m.getFullName(), m.getTier(), m.getMonthlyFee(), m.getStatus());
            }
        }

        System.out.println("+-----+--------------------+-----------+--------------+------------+");
        System.out.println("Total Records Displayed: " + members.size());
        System.out.println("----------------------------------------------------------------------------------\n");
    }
}