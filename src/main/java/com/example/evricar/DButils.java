package com.example.evricar;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class DButils {
	public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String mail) {
		Parent root = null;

		if (username != null) {
			try {
				FXMLLoader loader = new FXMLLoader(DButils.class.getResource(fxmlFile));
				root = loader.load();

				if (fxmlFile.equals("structureCatalog.fxml")) {
					CatalogController controller = loader.getController();
					controller.setHyperLink(username);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				root = FXMLLoader.load(Objects.requireNonNull(DButils.class.getResource(fxmlFile)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);

		if ((fxmlFile.equals("structureSignIn.fxml")) || (fxmlFile.equals("structureSignUp.fxml"))) {
			stage.setScene(new Scene(root, 950, 600));
			stage.show();
		} else {
			stage.setScene(new Scene(root, 1350, 780));
			stage.show();
		}
	}



	public static void signUpUSer(ActionEvent event, String username, String password, String mail) {
		Connection connection = null;
		PreparedStatement pstInsert = null;
		PreparedStatement pstCheckUserExists = null;
		ResultSet resultSets = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			pstCheckUserExists = connection.prepareStatement("SELECT username, password, mail FROM Autentificazione WHERE username=? OR mail=?");
			pstCheckUserExists.setString(1, username);
			pstCheckUserExists.setString(2, mail);
			resultSets = pstCheckUserExists.executeQuery();

			if (resultSets.isBeforeFirst()) {
				System.out.println("Exist");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Problems:\nThe user name might be already used\nYou already have an account with this mail\n");
				alert.show();
			} else {
				pstInsert = connection.prepareStatement("INSERT INTO Autentificazione (username, password, mail) VALUES (?, ?, ?)");
				pstInsert.setString(1, username);
				pstInsert.setString(2, password);
				pstInsert.setString(3, mail);
				pstInsert.executeUpdate();
				changeScene(event, "structureSignIn.fxml", "Welcome", username, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSets != null) {
				try {
					resultSets.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstCheckUserExists != null) {
				try {
					pstCheckUserExists.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstInsert != null) {
				try {
					pstInsert.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static void logInUSer(ActionEvent event, String mail, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("SELECT password, mail FROM Autentificazione WHERE username=?");
			preparedStatement.setString(1, mail);
			resultSets = preparedStatement.executeQuery();

			if (!resultSets.isBeforeFirst()) {
				System.out.println("User not found");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Wrong password");
				alert.show();
			} else {
				while (resultSets.next()) {
					String retrievePassword = resultSets.getString("password");
					String retriveMail = resultSets.getString("mail");
					if (retrievePassword.equals(password)) {
						if (retriveMail.contains("@impiegatiEvricar.com")) {
							StaticClass.nameImpiegati = mail;
							changeScene(event, "structureImpiegati.fxml", "Welcome", mail, retriveMail);
						} else if (retriveMail.contains("@segreteriaEvricar.com")) {
							StaticClass.nameSegreteria = mail;
							changeScene(event, "structureSegreteria.fxml", "Welcome", mail, retriveMail);
						} else {
							changeScene(event, "structureCatalog.fxml", "Welcome!", mail, retriveMail);
						}
					} else {
						System.out.println("Wrong password");
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("Wrong password");
						alert.show();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSets != null) {
				try {
					resultSets.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}



	public static void ChangeSceneMouseEvent(MouseEvent event, String fxmlFile, String title, int id) {
		Parent root = null;

		if (id != 0) {
			try {
				FXMLLoader loader = new FXMLLoader(DButils.class.getResource(fxmlFile));
				root = loader.load();
				ShowController controller = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				root = FXMLLoader.load(Objects.requireNonNull(DButils.class.getResource(fxmlFile)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);

		if ((fxmlFile.equals("structureSignIn.fxml")) || (fxmlFile.equals("structureSignUp.fxml"))) {
			stage.setScene(new Scene(root, 950, 600));
			stage.show();
		} else {
			stage.setScene(new Scene(root, 1350, 780));
			stage.show();
		}
	}



	public static void getInfo(MouseEvent event, int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;

		if (id != 0) {
			StaticClass.idAuto = id;
		}

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("SELECT descrizione,modello,marca, altezza ,lunghezza,larghezza,peso,volume_bagagliaio,prezzo FROM Automobili WHERE car_id=?");
			preparedStatement.setString(1, String.valueOf(id));
			resultSets = preparedStatement.executeQuery();

			if (!resultSets.isBeforeFirst()) {
				System.out.println("Car not found");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Car not found");
				alert.show();
			} else {
				while (resultSets.next()) {
					String retrieveDesc = resultSets.getString("descrizione");
					String retrieveMarca = resultSets.getString("marca");
					String retrieveModello = resultSets.getString("modello");
					String retriveAltezza = resultSets.getString("altezza");
					String retrieveLunghezza = resultSets.getString("lunghezza");
					String retrieveLarghezza = resultSets.getString("larghezza");
					String retrievePeso = resultSets.getString("peso");
					String retrieveVolumeBagagliaio = resultSets.getString("volume_bagagliaio");
					String retrievePrezzo = resultSets.getString("prezzo");

					StaticClass.marca = retrieveMarca;
					StaticClass.modello = retrieveModello;
					StaticClass.desc = retrieveDesc;
					StaticClass.alt = Float.parseFloat(retriveAltezza);
					StaticClass.lun = Float.parseFloat(retrieveLunghezza);
					StaticClass.lar = Float.parseFloat(retrieveLarghezza);
					StaticClass.peso = Float.parseFloat(retrievePeso);
					StaticClass.volume = Float.parseFloat(retrieveVolumeBagagliaio);
					StaticClass.prezzo = Float.parseFloat(retrievePrezzo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSets != null) {
				try {
					resultSets.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}



	public static void getId(ActionEvent event, String username) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("SELECT user_id FROM Autentificazione WHERE username=?");
			preparedStatement.setString(1, username);
			resultSets = preparedStatement.executeQuery();

			if (!resultSets.isBeforeFirst()) {
				System.out.println("not logged in");
			} else {
				while (resultSets.next()) {
					String retrieveId = resultSets.getString("user_id");
					StaticClass.idUser = Integer.parseInt(retrieveId);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public static void setPrev(StringBuilder str) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
		PreparedStatement pstInsert = connection.prepareStatement("INSERT INTO Preventivi (contenutoPrev) VALUES (?)");
		pstInsert.setString(1, String.valueOf(str));
		pstInsert.executeUpdate();
	}


	public static void getIdPrev(StringBuilder builder) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("SELECT id_preventivo FROM Preventivi WHERE contenutoPrev=?");
			preparedStatement.setString(1, String.valueOf(builder));
			resultSets = preparedStatement.executeQuery();

			if (!resultSets.isBeforeFirst()) {
				System.out.println("not logged in");
			} else {
				while (resultSets.next()) {
					String retrieveId = resultSets.getString("id_preventivo");
					StaticClass.idPreventivo = Integer.parseInt(retrieveId);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public static void setLink(int idUser, int idPreventivo, boolean used, String ritiro) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
		PreparedStatement pstInsert = connection.prepareStatement("INSERT INTO Utente_Preventivo (user_id,id_preventivo,Usato,Ritiro) VALUES (?,?,?,?)");
		pstInsert.setString(1, String.valueOf(idUser));
		pstInsert.setString(2, String.valueOf(idPreventivo));
		pstInsert.setString(3, String.valueOf(used));
		pstInsert.setString(4, String.valueOf(ritiro));
		pstInsert.executeUpdate();

		connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
		PreparedStatement pstInsert2 = connection.prepareStatement("INSERT INTO EsitoPreventivo (user_id,id_preventivo) VALUES (?,?)");
		pstInsert2.setString(1, String.valueOf(idUser));
		pstInsert2.setString(2, String.valueOf(idPreventivo));
		pstInsert2.executeUpdate();
	}


	public static void setTable() throws SQLException {		//tabella impiegati usato/non usato
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSets;
		ObservableList<String> listview = FXCollections.observableArrayList();
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("SELECT DISTINCT Autentificazione.username,Utente_Preventivo.id_preventivo,Utente_Preventivo.Usato,Utente_Preventivo.Ritiro FROM Utente_Preventivo INNER JOIN Autentificazione ON Usato=? WHERE Utente_Preventivo.user_id = Autentificazione.user_id ");
			preparedStatement.setString(1, "false");
			resultSets = preparedStatement.executeQuery();

			while (resultSets.next()) {
				String username = resultSets.getString("Autentificazione.username");
				String idPreventivo = resultSets.getString("Utente_Preventivo.id_preventivo");
				String isUsato = resultSets.getString("Utente_Preventivo.Usato");
				String ritiro = resultSets.getString("Utente_Preventivo.Ritiro");

				listview.add(username);
				listview.add(idPreventivo);
				listview.add(isUsato);
				listview.add(ritiro);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		StaticClass.prev = listview;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("SELECT DISTINCT Autentificazione.username,Utente_Preventivo.id_preventivo,Utente_Preventivo.Usato,Utente_Preventivo.Ritiro FROM Utente_Preventivo INNER JOIN Autentificazione ON Usato=? WHERE Utente_Preventivo.user_id = Autentificazione.user_id ");
			preparedStatement.setString(1, "true");
			resultSets = preparedStatement.executeQuery();

			while (resultSets.next()) {
				String username2 = resultSets.getString("Autentificazione.username");
				String idPreventivo2 = resultSets.getString("Utente_Preventivo.id_preventivo");
				String isUsato2 = resultSets.getString("Utente_Preventivo.Usato");
				String ritiro2 = resultSets.getString("Utente_Preventivo.Ritiro");

				list.add(username2);
				list.add(idPreventivo2);
				list.add(isUsato2);
				list.add(ritiro2);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		StaticClass.prevV = list;
	}



	public static void getAutoInfo() throws SQLException {		//info per tabella delle auto segreteria
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSets;
		ObservableList<String> listAuto = FXCollections.observableArrayList();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("SELECT car_id , marca , modello FROM Automobili");
			resultSets = preparedStatement.executeQuery();

			while (resultSets.next()) {
				String id1 = resultSets.getString("car_id");
				String marca1 = resultSets.getString("marca");
				String modello1 = resultSets.getString("modello");

				listAuto.add(id1);
				listAuto.add(marca1);
				listAuto.add(modello1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		StaticClass.auto = listAuto;
	}



	public static void getPrevCont(String codPreventivo) throws SQLException {
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSets;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("SELECT contenutoPrev FROM Preventivi  WHERE id_preventivo=?");
			preparedStatement.setString(1, StaticClass.codPreventivo);
			resultSets = preparedStatement.executeQuery();

			while (resultSets.next()) {
				String content = resultSets.getString("contenutoPrev");
				StaticClass.contenuto = content;

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public static void getIdFromImpiegati(ActionEvent event, String username) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("SELECT user_id FROM Autentificazione WHERE username=?");
			preparedStatement.setString(1, username);
			resultSets = preparedStatement.executeQuery();

			if (!resultSets.isBeforeFirst()) {
				System.out.println("not logged in");
			} else {
				while (resultSets.next()) {
					String retrieveId = resultSets.getString("user_id");
					StaticClass.idUserFromImpiegati = Integer.parseInt(retrieveId);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public static void eliminaPrev(String codPreventivo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("DELETE FROM Utente_Preventivo WHERE id_preventivo=?");
			preparedStatement.setString(1, codPreventivo);
			preparedStatement.executeUpdate();

			preparedStatement = connection.prepareStatement("DELETE FROM Preventivi WHERE id_preventivo=?");
			preparedStatement.setString(1, codPreventivo);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



	public static void updateTable(String result, String codPreventivo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("UPDATE EsitoPreventivo SET esito=? WHERE id_preventivo=?");
			preparedStatement.setString(1, result);
			preparedStatement.setString(2, codPreventivo);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public static void getIdFromCatalog(String idName) {
		Connection connection1 = null;
		PreparedStatement preparedStatement1 = null;
		ResultSet resultSets1 = null;
		try {
			connection1 = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement1 = connection1.prepareStatement("SELECT user_id FROM Autentificazione WHERE username=?");
			preparedStatement1.setString(1, idName);
			resultSets1 = preparedStatement1.executeQuery();

			if (!resultSets1.isBeforeFirst()) {
				System.out.println("HERE");
			} else {
				while (resultSets1.next()) {
					String retrieveId = resultSets1.getString("user_id");
					StaticClass.idUserFromCatalog = Integer.parseInt(retrieveId);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public static void getResult(int idUserFromCatalog) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("SELECT esito FROM EsitoPreventivo WHERE user_id=?");
			preparedStatement.setString(1, String.valueOf(idUserFromCatalog));
			resultSets = preparedStatement.executeQuery();
			if (!resultSets.isBeforeFirst()) {
				StaticClass.esito = "Null";
			} else {
				while (resultSets.next()) {
					String retrieveEsito = resultSets.getString("esito");
					StaticClass.esito = retrieveEsito;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public static void insertOptional(String text, String text1, float i) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;

		connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
		PreparedStatement pstInsert = connection.prepareStatement("INSERT INTO OptionalExtra (car_id,optional,prezzo) VALUES (?,?,?)");
		pstInsert.setString(1, String.valueOf(text));
		pstInsert.setString(2, String.valueOf(text1));
		pstInsert.setString(3, String.valueOf(i));
		pstInsert.executeUpdate();
	}


	public static void getExtraOptional(int idAuto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;
		String optional = "";
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("SELECT optional FROM OptionalExtra WHERE car_id=?");
			preparedStatement.setString(1, String.valueOf(idAuto));
			resultSets = preparedStatement.executeQuery();

			if (!resultSets.isBeforeFirst()) {
				System.out.println("HERE");
			} else {
				while (resultSets.next()) {
					String retrieveOptional = resultSets.getString("optional");
					optional = optional + retrieveOptional;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		StaticClass.lista = optional;
	}


	public static void updDesc(String text, String field, String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			String sql = "UPDATE Automobili SET " + field + "=? WHERE car_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, text);
			preparedStatement.setString(2, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public static void getExtraOptionalTable() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;

		ObservableList<String> id2 = FXCollections.observableArrayList();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("SELECT optional_id,car_id,optional FROM OptionalExtra");
			resultSets = preparedStatement.executeQuery();

			if (!resultSets.isBeforeFirst()) {
				System.out.println("HERE");
			} else {
				while (resultSets.next()) {
					String retrieveOpId = resultSets.getString("optional_id");
					String retrieveCarId = resultSets.getString("car_id");
					String retrieveOptional = resultSets.getString("optional");
					String addWord = retrieveOptional.replace("£", "");
					id2.add(retrieveOpId);
					id2.add(retrieveCarId);
					id2.add(addWord);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		StaticClass.idOptional = id2;
	}



	public static void eliminaOptional(String text) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("DELETE FROM OptionalExtra WHERE optional_id=?");
			preparedStatement.setString(1, text);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void setArrivo(String richiedente, String arrivata,int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			PreparedStatement pstInsert = connection.prepareStatement("INSERT INTO Arrivo (user_id,username,arrivo) VALUES (?,?,?)");
			pstInsert.setString(1, String.valueOf(id));
			pstInsert.setString(2, richiedente);
			pstInsert.setString(3, arrivata);
			pstInsert.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public static void getArrivo(int idUserFromCatalog) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("SELECT arrivo FROM Arrivo WHERE user_id=?");
			preparedStatement.setString(1, String.valueOf(idUserFromCatalog));
			resultSets = preparedStatement.executeQuery();
			if (!resultSets.isBeforeFirst()) {
				StaticClass.arrivo = "Null";
			} else {
				while (resultSets.next()) {
					String retrieveArrivo = resultSets.getString("arrivo");
					StaticClass.arrivo = retrieveArrivo;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public static void setTable2(String username) {		//area riservata utente
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSets;
		ObservableList<String> listview2 = FXCollections.observableArrayList();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("SELECT DISTINCT Utente_Preventivo.id_preventivo,Utente_Preventivo.Usato,Utente_Preventivo.Ritiro FROM Utente_Preventivo INNER JOIN Autentificazione ON Autentificazione.username=? WHERE Utente_Preventivo.user_id = Autentificazione.user_id ");
			preparedStatement.setString(1, username);
			resultSets = preparedStatement.executeQuery();

			while (resultSets.next()) {

				String idPreventivo = resultSets.getString("Utente_Preventivo.id_preventivo");
				String isUsato = resultSets.getString("Utente_Preventivo.Usato");
				String ritiro = resultSets.getString("Utente_Preventivo.Ritiro");

				listview2.add(idPreventivo);
				listview2.add(isUsato);
				listview2.add(ritiro);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		StaticClass.displayPrev = listview2;
	}


	public static void mantieniPrev(String codPreventivo, int idUserFromImpiegati) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSets = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cardealer", "root", "");
			preparedStatement = connection.prepareStatement("DELETE FROM Utente_Preventivo WHERE user_id = ? AND id_preventivo != ?");
			preparedStatement.setString(2, codPreventivo);
			preparedStatement.setString(1, String.valueOf(idUserFromImpiegati));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}