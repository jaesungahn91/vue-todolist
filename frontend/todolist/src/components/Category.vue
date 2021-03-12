<template>
  <div class="container">
    <h2>Category List</h2>
    <div class="input-group" style="margin-bottom:10px;">
      <input type="text" class="form-control" placeholder="카테고리를 입력하세요" v-model="category_name"
             v-on:keyup.enter="insertCategory(category_name)">
      <span class="input-group-btn">
    <button class="btn btn-default" type="button" @click="insertCategory(category_name)">추가</button>
  </span>
    </div>
    <ul class="list-group">

      <li class="list-group-item" v-for="(category, index) in categorys" v-on:click.self="detailCategory(category.category_id)">
        {{category.category_nm}}
        <div class="btn-group pull-right"
             style="font-size: 12px; line-height: 1;">

          <button class="btn btn-primary btn-xs" type="button"
                  data-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
          >수정</button>
          <ul class="dropdown-menu">
            <input type="text" class="form-control"
                   v-model="category.category_nm">
            <li><a @click="updateCategory(category.category_id, category.category_nm)">완료</a></li>
            <li><a href="#">취소</a></li>
          </ul>
          <button class="btn btn-danger btn-xs" type="button" @click="deleteCategory(category.category_id)">삭제</button>
        </div>
      </li>

    </ul>
  </div>
</template>

    <script>
    var url = "http://localhost:8080/category/";
    export default {
        name: 'category',
        methods: {
            getList() {
                this.$http.get(url+'getList')
                    .then((result) => {
                        console.log(result);
                        this.categorys = result.data;
                    })
            },
            insertCategory(category_name) {
                if (category_name != null) {
                    var postdata = new URLSearchParams();
                    postdata.append('category_name',category_name);
                    this.$http.defaults.headers.post['Content-Type'] = 'application/json';
                    this.$http.post(url+'insert', postdata).then((result) => {
                        location.href=url+"main";
                    });
                } else {
                    alert("카테고리를 입력하세요.");
                }
            },
            updateCategory(category_id, category_name) {
                if (category_name != null) {
                    var postdata = new URLSearchParams();
                    postdata.append('category_name', category_name);
                    postdata.append('category_id', category_id);
                    this.$http.defaults.headers.put['Content-Type'] = 'application/json';
                    this.$http.put(url+'update', postdata).then((result) => {
                    });
                } else {
                    alert("카테고리를 입력하세요.");
                }
            },
            deleteCategory(category_id) {
                this.$http.delete(url+'delete/'+category_id).then((result) => {
                    location.href=url+"main";
                });
            },
            detailCategory(category_id){
                location.href="http://localhost:8080/contents/"+category_id;
            }
        },
        data() {
            return {
                categorys: []
            }
        },
        mounted() {
            this.getList();
        }
    }
</script>

<style scoped>
  .list-group-item {
    height: 40px;
  }
</style>
