package com.javachinna.service;

import com.javachinna.model.Topic;
import com.javachinna.model.User;
import com.javachinna.repo.ITopicRepo;
import com.javachinna.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TopicService implements ITopicService{



    @Autowired
    ITopicRepo iTopicRepo;
    @Autowired
    UserRepository userRepository;


    @Override
    public Topic AddTopic(Topic topic) {
        return iTopicRepo.save(topic);
    }



    @Override
    public void AddAffectTopicList(Topic topic, Long idUser) {

        User user = (User) userRepository.findById(idUser).orElse(null);


                topic.setUser(user);
                iTopicRepo.save(topic);

    }

    @Override
    public Topic upDateTopic(Topic topic, Long idUser) {

        User user =  userRepository.findById(idUser).orElse(null);
        topic.setUser(user);
        return iTopicRepo.save(topic);
    }




    public void deleteTopic(Long idTopic) {
        log.info("In methode deleteTopic");
        log.warn("Are you sure you want to delete topic");

        iTopicRepo.deleteById(idTopic);
        log.error("exeption");

    }

    @Override
    public List<Topic> getAllTopics() {
        return (List<Topic>) iTopicRepo.findAll();
    }


    @Override
    public Integer getNbrCommentTopic(Long idTopic){
        Topic topic = iTopicRepo.findById(idTopic).orElse(null);
        return iTopicRepo.getNbrCommentTopic(idTopic);
    }


}
