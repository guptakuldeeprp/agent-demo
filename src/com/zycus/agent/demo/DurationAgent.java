package com.zycus.agent.demo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

public class DurationAgent {
	
	

	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("Executing premain.........");
		inst.addTransformer(new DurationTransformer());
	}

	public static class DurationTransformer implements ClassFileTransformer {

		@Override
		public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
				ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
			ClassPool cp = ClassPool.getDefault();
			CtClass cc;
			byte[] bytecode = classfileBuffer;
			if (className.equals("com/zycus/agent/demo/Hello")) {
				try {

					// cc = cp.get( "com.zycus.agent.demo.Hello");
					cc = cp.makeClass(new ByteArrayInputStream(classfileBuffer));
					CtField watchField = CtField.make(
							"public com.zycus.agent.demo.Stopwatch stopwatch = new com.zycus.agent.demo.Stopwatch();",
							cc);
					cc.addField(watchField);
					CtMethod m = cc.getDeclaredMethod("say");
					m.insertBefore("{ stopwatch.start(); }");
					m.insertAfter("{ stopwatch.stop(); System.out.println(\"The reading is: \" + stopwatch); }");
					bytecode = cc.toBytecode();

				} catch (NotFoundException e) {

					e.printStackTrace();
				} catch (CannotCompileException e) {

					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return bytecode;
		}

	}
}
