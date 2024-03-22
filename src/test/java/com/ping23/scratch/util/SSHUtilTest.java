package com.ping23.scratch.util;

import static org.junit.Assert.fail;

import org.junit.Test;

public class SSHUtilTest {

	@Test
	public void testSSHRemoteCommand() {
		
		final AppProperties secrets = new AppProperties("secret.properties");

		@SuppressWarnings("static-access")
		String sshUsername = secrets.getProperty("ssh.username");
		@SuppressWarnings("static-access")
		String sshPassword = secrets.getProperty("ssh.password");
		@SuppressWarnings("static-access")
		String sshHostname = secrets.getProperty("ssh.hostname");
		
		String output = null;
		try {
			output = SSHUtil.sshRemoteCommand(sshUsername, sshPassword, sshHostname, 22, "ls -lah");
		} catch (Exception e) {
			System.out.println(e);
			fail();
		}
		
		System.out.println(output);
	}

}
