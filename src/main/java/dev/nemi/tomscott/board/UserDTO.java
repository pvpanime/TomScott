package dev.nemi.tomscott.board;

public class UserDTO {
  public final String name;
  public final String passwdRaw;
  public UserDTO(String name, String passwdRaw) {
    this.name = name;
    this.passwdRaw = passwdRaw;
  }

  @Override
  public String toString() {
    return String.format("User(%s)", name);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof UserDTO && name.equals(((UserDTO) obj).name) && passwdRaw.equals(((UserDTO) obj).passwdRaw);
  }

}
