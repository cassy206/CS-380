module CS380_JavaFX_Project {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
}
