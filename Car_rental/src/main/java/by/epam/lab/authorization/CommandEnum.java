package by.epam.lab.authorization;

import by.epam.lab.commands.ActionCommand;
import by.epam.lab.commands.LoginCommand;
import by.epam.lab.commands.LogoutCommand;

public enum CommandEnum {
	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	};

	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
}
