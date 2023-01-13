package com.example.gtvtbe.service.impl;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.model.entity.NewsEntity;
import com.example.gtvtbe.model.response.NewsResponse;
import com.example.gtvtbe.model.response.PageResponse;
import com.example.gtvtbe.repository.NewsRepository;
import com.example.gtvtbe.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public Result<?> getByPage(Integer page, Integer pageSize) {
        var getPage = newsRepository.findAll(PageRequest.of(page-1, pageSize));
        PageResponse<List<NewsResponse>> pageResponse = new PageResponse<>();
        pageResponse.setPage(getPage.getTotalPages());
        pageResponse.setPageSize((long) getPage.getTotalPages());
        pageResponse.setData(
                getPage.get()
                        .map(item -> {
                            return new NewsResponse(
                                    item.getId(),
                                    item.getTitle(),
                                    item.getAvatar(),
                                    item.getContent(),
                                    item.getAuthor(),
                                    item.getCreatedTime(),
                                    item.getCategory()
                            );
                        }).collect(Collectors.toList())
        );
        return Result.ok(pageResponse);
    }

    @Override
    public Result<?> save(NewsEntity request) {
        request.setCreatedTime(System.currentTimeMillis());
        return Result.ok(newsRepository.save(request).getId());
    }

    @Override
    public Result<?> findById(String id) {
        return Result.ok(newsRepository.findById(id).map(item ->{
          return new NewsResponse(
                  item.getId(),
                  item.getTitle(),
                  item.getAvatar(),
                  item.getContent(),
                  item.getAuthor(),
                  item.getCreatedTime(),
                  item.getCategory()
          );
        }).get());
    }
}
