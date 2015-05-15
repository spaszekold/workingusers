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

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public void run(String... args) throws Exception {

        }

    public static void main(String[] args) {
        SpringApplication.run(WorkingusersApplication.class, args);
    }
}


//        PostEntity t = new PostEntity("Never in all their history have men been able truly to conceive of the world as one: a single sphere, a globe, having the qualities of a globe, a round earth in which all the directions eventually meet, in which there is no center because every point, or none, is center — an equal earth which all men occupy as equals. The airman's earth, if free men make it, will be truly round: a globe in practice, not in theory." +
//                "Science cuts two ways, of course; its products can be used for both good and evil. But there's no turning back from science. The early warnings about technological dangers also come from science. What was most significant about the lunar voyage was not that man set foot on the Moon but that they set eye on the earth. A Chinese tale tells of some men sent to harm a young girl who, upon seeing her beauty, become her protectors rather than her violators. That's how I felt seeing the Earth for the first time. I could not help but love and cherish her." +
//                "For those who have seen the Earth from space, and for the hundreds and perhaps thousands more who will, the experience most certainly changes your perspective. The things that we share in our world are far more valuable than those which divide us.",new Date(),"I believe every human has a finite number of heartbeats.","I don't intend to waste any of mine.");
//
//        CommentEntity c = new CommentEntity("you what mate", new Date(), "you wot", t);
//        CommentEntity x = new CommentEntity("you what 2 mate", new Date(), "you wot", t);
//        CommentEntity z = new CommentEntity("you what 3 mate", new Date(), "you wot", null);
//        commentRepository.save(c);
//        commentRepository.save(x);
//        commentRepository.save(z);
//
//        TagEntity tag = new TagEntity(null, "java");
//        TagEntity tag2 = new TagEntity(null, "kurczak");
//
//        tagRepository.save(tag);
//        tagRepository.save(tag2);
//        postRepository.save(t);
//        t.getTags().add(tag);
//        postRepository.save(t);
//        postRepository.save(new PostEntity("Will the reptilians rek us all?",new Date(),"Typical </h2> blog post","I require your attention."));
//        postRepository.save(new PostEntity("delo",new Date(),"Autism, attend me!","Ready Trump!"));
//        postRepository.save(new PostEntity("edlo",new Date(),"I wonder how do i shot web?","Actually, not anymore."));