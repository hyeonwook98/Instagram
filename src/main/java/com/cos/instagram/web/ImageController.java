package com.cos.instagram.web;

import com.cos.instagram.config.auth.PrincipalDetails;
import com.cos.instagram.domain.image.Image;
import com.cos.instagram.handler.ex.CustomValidationException;
import com.cos.instagram.service.ImageService;
import com.cos.instagram.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageService imageService;
    @GetMapping({"/", "/image/story"})
    public String story() {
        return "image/story";
    }

    @GetMapping({"/image/popular"})
    public String popular(Model model) {

        List<Image> images = imageService.인기사진();
        model.addAttribute("images", images);
        return "image/popular";
    }

    @GetMapping({"/image/upload"})
    public String upload() {
        return "image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (imageUploadDto.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
        }
        imageService.사진업로드(imageUploadDto, principalDetails);
        return "redirect:/user/" + principalDetails.getUser().getId();
    }
}
