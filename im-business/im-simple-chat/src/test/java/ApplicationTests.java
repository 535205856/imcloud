import cn.hutool.core.util.IdUtil;
import com.awesome.simple.chat.SimpleChatApplicationStart;
import com.awesome.simple.chat.entity.DispatchMessage;
import com.awesome.simple.chat.entity.PushMessage;
import com.awesome.simple.chat.mapper.DispatchMessageMapper;
import com.awesome.simple.chat.mapper.PushMessageMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * projectName：im-dispatcher
 * className ：ApplicationTests
 * class desc：TODO
 * createTime：2019/11/13 4:48 PM
 * creator：awesome
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleChatApplicationStart.class)
public class ApplicationTests {

    @Autowired
    DispatchMessageMapper dispatchMessageMapper;

    @Autowired
    PushMessageMapper pushMessageMapper;
    @Test
    public void contextLoads() {
//        final List<Map> maps =
//                chatHistoryMessageDao.selecMessageeHisstoryByMessageIds
//                        (Arrays.asList(IdUtil.createSnowflake(10,10).nextId(),
//                                IdUtil.createSnowflake(10,10).nextId()));
//
//        for (int i = 0; i < 100; i++) {
//            DispatchMessage chatHistoryMessage=new DispatchMessage();
//            chatHistoryMessage.setMessageContent(IdUtil.simpleUUID());
//            chatHistoryMessage.setSendTime(new Date());
//            chatHistoryMessage.setMessageType("1");
//            chatHistoryMessage.setSendUserId(IdUtil.simpleUUID());
//            chatHistoryMessage.setReceiveUserId(IdUtil.simpleUUID());
//            dispatchMessageMapper.insert(chatHistoryMessage);
//        }


        for (int i = 0; i < 1000; i++) {
            PushMessage pushMessage=new PushMessage();
            pushMessage.setMessageContent(IdUtil.simpleUUID());
            pushMessage.setSendTime(new Date());
            pushMessage.setMessageType("1");
            pushMessage.setSendUserId(IdUtil.simpleUUID());
            pushMessage.setReceiveUserId(IdUtil.simpleUUID());
            pushMessage.setDelivered(0);
            pushMessageMapper.insert(pushMessage);
        }


//        QueryWrapper<ChatHistoryMessage> queryWrapper =  new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
//        Page<ChatHistoryMessage> page = new Page<>(1,5);  // 查询第1页，每页返回5条
//        IPage<ChatHistoryMessage> chatHistoryMessageIPage = chatHistoryMessageDao.selectPage(page, queryWrapper);
//        long pages = chatHistoryMessageIPage.getPages();
//        System.err.println("pages: "+pages);
//        System.err.println(chatHistoryMessageIPage.getRecords().size());
//        for (ChatHistoryMessage record : chatHistoryMessageIPage.getRecords()) {
//            System.out.println(record);
//
//        }



//        dispatchMessageMapper.insert(chatHistoryMessage);

    }



}