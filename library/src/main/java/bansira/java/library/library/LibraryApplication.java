package bansira.java.library.library;

import bansira.java.library.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class LibraryApplication {
	@Autowired
	BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Scheduled(cron = "0 0 0 * * ?") // Reset Hits Everyday at midnight
	public void resetValueDaily() {
		bookService.resetDaily();
	}
	@Scheduled(cron = "0 0 0 ? * MON") // Reset Hits Every Week
	public void resetValueMonthly() {
		bookService.resetMonthly();
	}
	@Scheduled(cron = "0 0 0 1 * ?") // Reset Hits Every Month
	public void resetValueWeekly() {
		bookService.resetWeekly();
	}
}
