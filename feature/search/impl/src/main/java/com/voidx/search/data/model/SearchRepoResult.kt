package com.voidx.search.data.model

import com.voidx.repo.data.model.Repo

class SearchRepoResult(items: List<Repo>, hasNextPage: Boolean) : SearchResult<Repo>(items,
    hasNextPage
)