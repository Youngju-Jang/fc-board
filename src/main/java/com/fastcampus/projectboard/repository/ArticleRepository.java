package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>, // Article 안의 모든필드의 검색기능을 추가함
        QuerydslBinderCustomizer<QArticle>
{
    Page<Article> findByTitle(String title, Pageable pageable);
    
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true); // 리스팅하지않은 property는 검색제외시키기
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy); // 이후 id추가필요
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%${v}%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%${v}%'
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase); // like '%${v}%'
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // like '%${v}%'
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // like '%${v}%'
        //bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like ' ${v}'  << 부분검색시 필요한 %의 유무를  수동으로 정하고싶을때 사용
    }

}
