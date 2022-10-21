package com.capstone.skone.auction.auth.board.application;

import com.capstone.skone.auction.auth.board.domain.File;
import com.capstone.skone.auction.auth.board.dto.CreateFileDto;
import com.capstone.skone.auction.auth.board.infrastructure.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileService {
  private final FileRepository fileRepository;

  @Transactional
  public String saveFile(CreateFileDto fileDto) {
    return fileRepository.save(fileDto.toEntity()).getOrigFilename();
  }

  @Transactional
  public CreateFileDto getFile(Long id) {
    File file = fileRepository.findById(id).get();
    return CreateFileDto.builder()
        .id(id)
        .origFilename(file.getOrigFilename())
        .filename(file.getFilename())
        .filePath(file.getFilePath())
        .build();
  }
}