package workingusers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import workingusers.entity.CommentEntity;
import workingusers.entity.PostEntity;
import workingusers.entity.TagEntity;
import workingusers.repository.CommentRepository;
import workingusers.repository.PostRepository;
import workingusers.repository.TagRepository;

import java.util.Date;

@SpringBootApplication
public class WorkingusersApplication implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        }

    public static void main(String[] args) {
        SpringApplication.run(WorkingusersApplication.class, args);
    }
}

