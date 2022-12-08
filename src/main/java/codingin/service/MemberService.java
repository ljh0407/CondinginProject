package codingin.service;

import codingin.domain.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    //=============전역변수=============================//
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LetterRepository letterRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private RereplyRepository rereplyRepository;
    @Autowired
    private  UpdownRepository updownRepository;


    //====================================================//

}
