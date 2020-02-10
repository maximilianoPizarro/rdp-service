//http://www.objectaid.com/class-diagram
//https://tohtml.com/java/
package com.rdp;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.negocio.UpdateJob;

/**
 * Hello world!
 *
 */
public class App extends Application {

	private boolean firstTime;
	private TrayIcon trayIcon;
	

	@Override
	public void start(Stage stage) throws Exception {

		
		JobDetail job = JobBuilder.newJob(UpdateJob.class).withIdentity("dummyJobName", "group1").build();
		//https://www.google.com/search?q=convertir+segundos+en+horas&ie=utf-8&oe=utf-8&client=firefox-b-ab
		// Trigger the job to run on the next round minute
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName", "group1")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(7200).repeatForever()).build();  //cada 2 horas
		// schedule it
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
		 
		Parent root = FXMLLoader.load(getClass().getResource("/views/index.fxml"));
		
		//Font.loadFont(getClass().getResource("/views/bastrap3/fonts/CHANEWEI.TTF").toExternalForm(), 10);
		
		
		createTrayIcon(stage);
		firstTime = true;
		Platform.setImplicitExit(false);
		Scene scene = new Scene(root, 615, 374);
		stage.setTitle("DGCACTYSV");
		stage.setMaxWidth(615);
		stage.setMaxHeight(400);
		
		stage.setScene(scene);
		stage.setResizable(false);
		// stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	// icono en la barra de tareas
	public void createTrayIcon(final Stage stage) {
		if (SystemTray.isSupported()) {
			// get the SystemTray instance
			SystemTray tray = SystemTray.getSystemTray();
			// load an image
			java.awt.Image image = null;
			try {
				URL url = App.class.getResource("/static/favicon_ba.png");
				//URL url = new URL("http://10.68.11.218/rdp-agente/static/favicon_ba.png");
				image = ImageIO.read(url);
				//icono de la url
				stage.getIcons().add(new Image("https://rdp-gcba.herokuapp.com/static/rdp-agente/static/favicon_ba.png"));
				if(stage.getIcons().isEmpty())
					stage.getIcons().add(new Image("/static/favicon_ba.png"));
				
			} catch (IOException ex) {
				System.out.println(ex);
			}

			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					hide(stage);
				}
			});
			// create a action listener to listen for default action executed on
			// the tray icon
			final ActionListener closeListener = new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			};

			ActionListener showListener = new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/views/index.fxml")),600,400));
								stage.show();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
				}
			};
			// create a popup menu
			PopupMenu popup = new PopupMenu();

			MenuItem showItem = new MenuItem("Abrir");
			showItem.addActionListener(showListener);
			popup.add(showItem);

			MenuItem closeItem = new MenuItem("Cerrar");
			closeItem.addActionListener(closeListener);
			popup.add(closeItem);
			/// ... add other items
			// construct a TrayIcon
			trayIcon = new TrayIcon(image, "RDP", popup);   //aca los datos del icono
			// set the TrayIcon properties
			trayIcon.addActionListener(showListener);
			// ...
			// add the tray image
			try {
				tray.add(trayIcon);
			} catch (AWTException e) {				
				System.err.println(e);
			}
			// ...
		}
	}

	public void showProgramIsMinimizedMsg() {
		if (firstTime) {
			trayIcon.displayMessage("Error de conexion de area local", "Compruebe si el cable de red esta conectado y vuelva a reiniciar", TrayIcon.MessageType.INFO);
			firstTime = false;
		}
	}

	private void hide(final Stage stage) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (SystemTray.isSupported()) {
					stage.hide();
					// showProgramIsMinimizedMsg();
				} else {
					System.exit(0);
				}
			}
		});
	}

}
