package by.epam.lab.command;

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
	},
	MAIN_PAGE {
		{
			this.command = new CreateCatalogCommand();
		}
	},
	CAR_DESCRIPTION_PAGE {
		{
			this.command = new CarDescriptionCommand();
		}
	},
	CHECK_PASSPORT_DATA {
		{
			this.command = new CheckPassportDataCommand();
		}
	},
	ADD_PASSPORT_DATA {
		{
			this.command = new AddPaassportDataCommand();
		}
	},
	BOOK_CAR {
		{
			this.command = new BookCarCommand();
		}
	};

	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
}
