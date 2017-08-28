package by.htp.task01.controller.command.impl;

import by.htp.task01.controller.command.Command;

public class WrongRequest implements Command {

	@Override
	public String executeCommand(String request) {
		return "Wrong request!";
	}

}
