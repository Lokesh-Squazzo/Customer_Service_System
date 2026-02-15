package com.customerservice.presentation;

import java.util.Scanner;

public class InputReader {
    Scanner s;

    public InputReader() {
        this.s = new Scanner(System.in);
    }

    public int getInt() {
        return Integer.parseInt(this.s.nextLine());
    }

    public long getLong() {
        return Long.parseLong(this.s.nextLine());
    }

    public String getString() {
        return this.s.nextLine();
    }
}
