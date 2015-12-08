package by.huryanchyk.entity;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p>
 * The {@code User} class is an entity-class which includes all
 * the necessary getters and setters for its fields and also overrides
 * {@code equals()}, {@code hashCode()} and {@code toString()}.
 */
public class User {

    private String name;
    private String surName;
    private String login;
    private String email;
    private String phoneNumber;

    /**
     * Default constructor of the {@code User} class.
     */
    public User() {
    }

    /**
     * Constructor with parameters
     *
     * @param name        is the first name of an user.
     * @param surName     is the last name of an user.
     * @param login       is a login of an user.
     * @param email       is a email of an user.
     * @param phoneNumber is a phone number of an user.
     */
    public User(String name, String surName, String login, String email, String phoneNumber) {
        this.name = name;
        this.surName = surName;
        this.login = login;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets user's value of name.
     *
     * @return name value.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name value of an user.
     *
     * @param name is a name value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets user's value of surname.
     *
     * @return surname value.
     */
    public String getSurName() {
        return surName;
    }

    /**
     * Sets surname value of an user.
     *
     * @param surName is a surname value.
     */
    public void setSurName(String surName) {
        this.surName = surName;
    }

    /**
     * Gets user's value of login.
     *
     * @return login value.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login value of an user.
     *
     * @param login is a login value.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets user's value of e-mail.
     *
     * @return e-mail value.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email value of an user.
     *
     * @param email is a email value.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets user's value of phone number.
     *
     * @return phone number value.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone numbser value of an user.
     *
     * @param phoneNumber is a phone number value.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Determines the rules of comparison of some other object {@code o}
     * with this one. The rules depends on the fields of {@code User class.
     *
     * @param o gives a link to an object with which it is necessary
     *          to compare this one.
     * @return {@code true} if the object {@param o} satisfies the rules
     * of comparison with this one, otherwise returns {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getSurName() != null ? !getSurName().equals(user.getSurName()) : user.getSurName() != null) return false;
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        return !(getPhoneNumber() != null ? !getPhoneNumber().equals(user.getPhoneNumber()) : user.getPhoneNumber() != null);

    }

    /**
     * Gives a hash code value for the object according to the rules of
     * hash code generation.
     *
     * @return an integer value that corresponds an object.
     */
    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getSurName() != null ? getSurName().hashCode() : 0);
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        return result;
    }

    /**
     * Gives a textual representation of the object according to
     * definite rules presented in the method.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", surName='").append(surName).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
