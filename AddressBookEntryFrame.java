// Java core packages
import java.util.*;
import java.awt.*;

// Java extension packages
import javax.swing.*;

public class AddressBookEntryFrame extends JInternalFrame {

    // HashMap to store JTextField references for quick access
    private HashMap fields;

    // current AddressBookEntry set by AddressBook application
    private AddressBookEntry person;

    // panels to organize GUI
    private JPanel leftPanel, rightPanel;
    private JPanel leftPanel_2, rightPanel_2;

    // static integers used to determine new window positions
    // for cascading windows
    private static int xOffset = 0, yOffset = 0;

    // static Strings that represent name of each text field.
    // These are placed on JLabels and used as keys in
    // HashMap fields.
    private static final String FIRST_NAME = "First Name",
            LAST_NAME = "Last Name",
            ADDRESS1 = "Address 1", ADDRESS2 = "Address 2", CITY = "City", STATE = "State", EIRCODE = "Eircode",
            ADDRESS1_1 = "Address 1(2)", ADDRESS2_1 = "Address 2(2)", CITY_1 = "City(2)", STATE_1 = "State(2)", EIRCODE_1 = "Eircode(2)",
            ADDRESS1_2 = "Address 1(3)", ADDRESS2_2 = "Address 2(3)", CITY_2 = "City(3)", STATE_2 = "State(3)", EIRCODE_2 = "Eircode(3)",
            PHONE = "Phone", EMAIL = "Email",
            PHONE_1 = "Phone(2)", EMAIL_1 = "Email(2)",
            PHONE_2 = "Phone(3)", EMAIL_2 = "Email(3)";

    private int newAddressClickCount = 0;
    private int addPhoneNumbersClickCount = 0;
    private int addEmailAddressClickCount = 0;
    private int rowCount = 9;
    private int height = 300;
    private Container container;

    // construct GUI
    public AddressBookEntryFrame()
    {
        super( "Address Book Entry", true, true );

        fields = new HashMap();

        leftPanel = new JPanel();
        leftPanel.setLayout( new GridLayout( rowCount, 1, 0, 5 ) );
        rightPanel = new JPanel();
        rightPanel.setLayout( new GridLayout( rowCount, 1, 0, 5 ) );

        leftPanel_2 = new JPanel();
        leftPanel_2.setLayout( new GridLayout( rowCount, 1, 0, 5 ) );
        rightPanel_2 = new JPanel();
        rightPanel_2.setLayout( new GridLayout( rowCount, 1, 0, 5 ) );

        createRow( FIRST_NAME , 1);
        createRow( LAST_NAME , 1);
        createRow( ADDRESS1 , 1);
        createRow( ADDRESS2 , 1);
        createRow( CITY , 1);
        createRow( STATE , 1);
        createRow( EIRCODE , 1);
        createRow( PHONE , 1);
        createRow( EMAIL , 1);

        container = getContentPane();
        container.add( leftPanel, BorderLayout.WEST );
        container.add( rightPanel, BorderLayout.CENTER );
        container.add( leftPanel_2, BorderLayout.EAST );
        container.add( rightPanel_2, BorderLayout.EAST );

        setBounds( xOffset, yOffset, 300, height );
        xOffset = ( xOffset + 30 ) % 300;
        yOffset = ( yOffset + 30 ) % 300;
    }

    public void addPhoneNumbers() {
        addPhoneNumbersClickCount++;
        switch (addPhoneNumbersClickCount) {
            case 1:
                createRow( PHONE_1 , 2);
                rowCount += 1;
                leftPanel.setLayout( new GridLayout( rowCount, 1, 0, 5 ) );
                rightPanel.setLayout( new GridLayout( rowCount, 1, 0, 5 ) );
                height += 50;
                setBounds(xOffset, yOffset, 300, height);
                container.revalidate();
                break;
            case 2:
                createRow( PHONE_2 , 2);
                rowCount += 1;
                leftPanel.setLayout( new GridLayout( rowCount, 1, 0, 5 ) );
                rightPanel.setLayout( new GridLayout( rowCount, 1, 0, 5 ) );
                height += 50;
                setBounds(xOffset, yOffset, 300, height);
                container.revalidate();
                break;
        }
    }
    public void addAddresses() {
        newAddressClickCount++;
        switch (newAddressClickCount) {
            case 1:
                createRow( ADDRESS1_1 , 2);
                createRow( ADDRESS2_1 , 2);
                createRow( CITY_1 , 2);
                createRow( STATE_1 , 2);
                createRow( EIRCODE_1 , 2);
                rowCount += 5;
                leftPanel.setLayout( new GridLayout( rowCount, 1, 0, 5 ) );
                rightPanel.setLayout( new GridLayout( rowCount, 1, 0, 5 ) );
                height += 150;
                setBounds(xOffset, yOffset, 300, height);
                container.revalidate();
                break;
            case 2:
                createRow( ADDRESS1_2 , 2);
                createRow( ADDRESS2_2 , 2);
                createRow( CITY_2 , 2);
                createRow( STATE_2 , 2);
                createRow( EIRCODE_2 , 2);
                rowCount += 5;
                leftPanel.setLayout( new GridLayout( rowCount, 1, 0, 5 ) );
                rightPanel.setLayout( new GridLayout( rowCount, 1, 0, 5 ) );
                height += 150;
                setBounds(xOffset, yOffset, 300, height);
                container.revalidate();
                break;
        }

    }

    // set AddressBookEntry then use its properties to
    // place data in each JTextField
    public void setAddressBookEntry( AddressBookEntry entry )
    {
        person = entry;

        setField( FIRST_NAME, person.getFirstName() );
        setField( LAST_NAME, person.getLastName() );
        setField( ADDRESS1, person.getAddress1() );
        setField( ADDRESS2, person.getAddress2() );
        setField( CITY, person.getCity() );
        setField( STATE, person.getState() );
        setField( EIRCODE, person.getZipcode() );
        setField( PHONE, person.getPhoneNumber() );
        setField( EMAIL, person.getEmailAddress() );
    }

    // store AddressBookEntry data from GUI and return
    // AddressBookEntry
    public AddressBookEntry getAddressBookEntry()
    {
        person.setFirstName( getField( FIRST_NAME ) );
        person.setLastName( getField( LAST_NAME ) );
        person.setAddress1( getField( ADDRESS1 ) );
        person.setAddress2( getField( ADDRESS2 ) );
        person.setCity( getField( CITY ) );
        person.setState( getField( STATE ) );
        person.setZipcode( getField(EIRCODE) );
        person.setPhoneNumber( getField( PHONE ) );
        person.setEmailAddress( getField( EMAIL ) );

        return person;
    }

    // set text in JTextField by specifying field's
    // name and value
    private void setField( String fieldName, String value )
    {
        JTextField field =
                ( JTextField ) fields.get( fieldName );

        field.setText( value );
    }

    // get text in JTextField by specifying field's name
    private String getField( String fieldName )
    {
        JTextField field =
                ( JTextField ) fields.get( fieldName );

        return field.getText();
    }

    // utility method used by constructor to create one row in
    // GUI containing JLabel and JTextField
    private void createRow( String name, int side) {

        JLabel label = new JLabel(name, SwingConstants.RIGHT);
        label.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JTextField field = new JTextField(30);

        switch (side) {
            case 1:
                leftPanel.add(label);
                rightPanel.add(field);

                fields.put(name, field);
                break;
            case 2:
                leftPanel_2.add(label);
                rightPanel_2.add(field);

                fields.put(name, field);
                break;
        }
    }
}  // end class AddressBookEntryFrame
