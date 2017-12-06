package com.common.dao;

public class TT {
	public static void main(String[] args) {
		for (int i = -4; i <= 4; i++) {
			// 先减少再增加 步长为1
			for (int j = 0; j <= Math.abs(i) + 10; j++) {
				System.out.print(" ");
			}
			// 先增加后减少 步长为2
			for (int k = 0; k < 9 - Math.abs(i) * 2; k++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
