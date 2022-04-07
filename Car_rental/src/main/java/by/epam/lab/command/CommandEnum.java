package by.epam.lab.command;

import by.epam.lab.command.general.SetLocaleCommand;
import by.epam.lab.command.manager.AcceptOrderCommand;
import by.epam.lab.command.manager.AddCarCommand;
import by.epam.lab.command.manager.GetGoodsCommand;
import by.epam.lab.command.manager.GoodsInfoCommand;
import by.epam.lab.command.manager.GoodsInfoModificationCommand;
import by.epam.lab.command.manager.ManagerMainCommand;
import by.epam.lab.command.manager.OrderInformationCommand;
import by.epam.lab.command.manager.RegisterDamageCommand;
import by.epam.lab.command.manager.RejectOrderCommand;
import by.epam.lab.command.manager.ReturnCarCommand;
import by.epam.lab.command.order.AddPaassportDataCommand;
import by.epam.lab.command.order.BookCarCommand;
import by.epam.lab.command.order.CarDescriptionCommand;
import by.epam.lab.command.send.ToAddPageCommand;
import by.epam.lab.command.send.ToRegisterDamageCommand;
import by.epam.lab.command.send.ToInputDatesCommand;
import by.epam.lab.command.send.ToLoginPageCommand;
import by.epam.lab.command.send.ToPassportDataInputPageCommand;
import by.epam.lab.command.send.ToPaymentMakingCommand;
import by.epam.lab.command.send.ToRegistrationPageCommand;
import by.epam.lab.command.user.CancelOrderCommand;
import by.epam.lab.command.user.CreateCatalogCommand;
import by.epam.lab.command.user.LoginCommand;
import by.epam.lab.command.user.LogoutCommand;
import by.epam.lab.command.user.PayOrderCommand;
import by.epam.lab.command.user.UserOrderInfoCommand;
import by.epam.lab.command.user.UserOrdersCommand;
import by.epam.lab.command.user.UserRegistration;

public enum CommandEnum {
	TO_LOGIN_PAGE {
		{
			this.command = new ToLoginPageCommand();
		}
	},
	TO_REGISTRATION_PAGE {
		{
			this.command = new ToRegistrationPageCommand();
		}
	},
	SET_LOCALE {
		{
			this.command = new SetLocaleCommand();
		}
	},
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
	ADD_PASSPORT_DATA {
		{
			this.command = new AddPaassportDataCommand();
		}
	},
	BOOK_CAR {
		{
			this.command = new BookCarCommand();
		}
	},
	MANAGER_MAIN {
		{
			this.command = new ManagerMainCommand();
		}
	},
	USER_REGISTRATION {
		{
			this.command = new UserRegistration();
		}
	},
	ORDER_INFORMATION {
		{
			this.command = new OrderInformationCommand();
		}
	},
	ACCEPT_ORDER {
		{
			this.command = new AcceptOrderCommand();
		}
	},
	REJECT_ORDER {
		{
			this.command = new RejectOrderCommand();
		}
	},
	USER_ORDERS {
		{
			this.command = new UserOrdersCommand();
		}
	},
	CANCEL_ORDER {
		{
			this.command = new CancelOrderCommand();
		}
	},
	GET_GOODS {
		{
			this.command = new GetGoodsCommand();
		}
	},
	GOODS_INFO_MODIFICATION {
		{
			this.command = new GoodsInfoModificationCommand();
		}
	},
	GOODS_INFO {
		{
			this.command = new GoodsInfoCommand();
		}
	},
	TO_ADD_PAGE {
		{
			this.command = new ToAddPageCommand();
		}
	},
	ADD_CAR {
		{
			this.command = new AddCarCommand();
		}
	},
	USER_ORDER_INFO {
		{
			this.command = new UserOrderInfoCommand();
		}
	},
	PAY_ORDER {
		{
			this.command = new PayOrderCommand();
		}
	},
	TO_PASSPORT_DATA_INPUT {
		{
			this.command = new ToPassportDataInputPageCommand();
		}
	},
	TO_INPUT_DATES {
		{
			this.command = new ToInputDatesCommand();
		}
	},
	RETURN_CAR {
		{
			this.command = new ReturnCarCommand();
		}
	},
	TO_DAMAGE_REGISTRATION {
		{
			this.command = new ToRegisterDamageCommand();
		}
	},
	REGISTER_DAMAGE {
		{
			this.command = new RegisterDamageCommand();
		}
	},
	TO_PAYMENT_MAKING {
		{
			this.command = new ToPaymentMakingCommand();
		}
	};

	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
}
