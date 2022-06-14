package com.twoCube.gifts.service;

import com.twoCube.common.EBucketType;
import com.twoCube.common.S3Uploader;
import com.twoCube.couple.domain.Couple;
import com.twoCube.gifts.domain.GiftPolaroid;
import com.twoCube.gifts.domain.GiftVoicemail;
import com.twoCube.gifts.dto.detail.UserAudioResponse;
import com.twoCube.gifts.dto.detail.UserPolaroidResponse;
import com.twoCube.gifts.dto.list.GiftPolaroidResponse;
import com.twoCube.gifts.dto.list.GiftVoiceMailResponse;
import com.twoCube.gifts.repository.GiftPolaroidRepository;
import com.twoCube.gifts.repository.GiftVoicemailRepository;
import com.twoCube.members.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftVoicemailServiceImpl implements GiftVoiceMailService{

    private final S3Uploader s3Uploader;
    private final GiftVoicemailRepository giftVoicemailRepository;


    @Override
    public Long createVoicemail(MultipartFile voicemail, String title, Member member) {
        Couple couple = member.getCouple();
        GiftVoicemail giftVoicemail = null;

        try {
            String imageUrl = s3Uploader.upload(voicemail, EBucketType.voicemail);
            giftVoicemail = GiftVoicemail.builder().title(title).couple(couple)
                    .member(member).voicemail(imageUrl).build();

            giftVoicemailRepository.save(giftVoicemail);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return giftVoicemail.getId();
    }

    @Override
    public List<GiftVoiceMailResponse> getVoiceMailList(Member member) {
        List<GiftVoicemail> giftVoicemailList =
                giftVoicemailRepository.findAllByCouple(member.getCouple());
        return GiftVoiceMailResponse.listFrom(giftVoicemailList);
    }

    @Override
    public UserAudioResponse getVoiceMail(Long id) {
        GiftVoicemail giftVoicemail =
                giftVoicemailRepository.getById(id);
        return UserAudioResponse.from(giftVoicemail);
    }
}
