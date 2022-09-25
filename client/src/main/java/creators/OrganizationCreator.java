package creators;

import io.Terminal;
import exceptions.FieldDefinitionException;
import model.Organization;
import model.OrganizationType;

public class OrganizationCreator extends AbstractCreator<Organization> {
    private final CoordinatesCreator coordinatesCreator;
    private final AddressCreator addressCreator;

    private enum OrganizationArgument {
        NAME, COORDINATES, ANNUAL_TURNOVER, FULL_NAME, EMPLOYEES_COUNT, TYPE, ADDRESS
    }

    private OrganizationArgument lastSetArgument;

    public OrganizationCreator(Terminal terminal) {
        super(terminal);
        this.coordinatesCreator = new CoordinatesCreator(terminal);
        this.addressCreator = new AddressCreator(terminal);
        this.lastSetArgument = OrganizationArgument.ADDRESS;
    }

    @Override
    public Organization createNewInstance() {
        return new Organization();
    }

    @Override
    protected void defineFields() throws FieldDefinitionException {
        switch (lastSetArgument) {
        case ADDRESS:
            defineName();
        case NAME:
            defineCoordinates();
        case COORDINATES:
            defineAnnualTurnover();
        case ANNUAL_TURNOVER:
            defineFullName();
        case FULL_NAME:
            defineEmployeesCount();
        case EMPLOYEES_COUNT:
            defineType();
        case TYPE:
            defineAddress();
        }
        // break statement MUST NOT be here
    }

    private void defineName() {
        String input = terminal.readLineEntire(
                "Введите название организации: " + formatRequirements("String, not null, not empty string"));
        creatingObject.setName(input);
        this.lastSetArgument = OrganizationArgument.NAME;
    }

    private void defineCoordinates() {
        creatingObject.setCoordinates(coordinatesCreator.create());
        this.lastSetArgument = OrganizationArgument.COORDINATES;
    }

    private void defineAnnualTurnover() {
        String[] input = terminal
                .readLineSplit("Введите ежегодный товарооборот: " + formatRequirements("Long, not null, > 0"));
        checkArgumentsAmount(input, 1);
        try {
            Long annualTurnover = Long.parseLong(input[0]);
            creatingObject.setAnnualTurnover(annualTurnover);
        } catch (NumberFormatException e) {
            throw new FieldDefinitionException("Ожидалось целое число (Long)");
        }
        this.lastSetArgument = OrganizationArgument.ANNUAL_TURNOVER;
    }

    private void defineFullName() {
        String input = terminal.readLineEntire(
                "Введите полное название организации: " + formatRequirements("String, not null, unique"));
        creatingObject.setFullName(input);
        this.lastSetArgument = OrganizationArgument.FULL_NAME;
    }

    /*
     * private void checkFullNameUniqueness(String fullName) { for (Organization org : dataCollection) { if
     * (org.getFullName().equals(fullName)) throw new
     * FieldDefinitionException(String.format("Полное имя \"%s\" неуникально", fullName)); } }
     */

    private void defineEmployeesCount() {
        String[] input = terminal.readLineSplit("Введите число сотрудников: " + formatRequirements("int, > 0"));
        checkArgumentsAmount(input, 1);
        try {
            int employeesCount = Integer.parseInt(input[0]);
            creatingObject.setEmployeesCount(employeesCount);
        } catch (NumberFormatException e) {
            throw new FieldDefinitionException("Ожидалось целое число (int)");
        }
        this.lastSetArgument = OrganizationArgument.EMPLOYEES_COUNT;
    }

    private void defineType() {
        String message = "Выберите тип организации\n" + "0 - коммерческая организация\n" + "1 - траст\n"
                + "2 - общество с ограниченной ответственностью\n" + "3 - открытое акционерное общество\n"
                + "Введите номер варианта: ";
        String[] input = terminal.readLineSplit(message);
        checkArgumentsAmount(input, 1);
        try {
            creatingObject.setType(OrganizationType.getByID(Integer.parseInt(input[0])));
        } catch (NumberFormatException | OrganizationType.InvalidIDException e) {
            throw new FieldDefinitionException("Ожидалось целое число от 0 до 3");
        }
        this.lastSetArgument = OrganizationArgument.TYPE;
    }

    private void defineAddress() {
        creatingObject.setOfficialAddress(addressCreator.create());
        this.lastSetArgument = OrganizationArgument.ADDRESS;
    }
}
