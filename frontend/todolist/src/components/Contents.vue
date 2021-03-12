<template>
  <div class="container">
    <h2>Contents List</h2>
    <div class="input-group" style="margin-bottom:10px;">
      <input type="text" class="form-control" placeholder="할일을 입력하세요" v-model="contents_name"
             v-on:keyup.enter="insertContents(contents_name)">
      <span class="input-group-btn">
      <button class="btn btn-default" type="button" @click="insertContents(contents_name)">입력</button>
    </span>
    </div>
    <ul class="list-group">

      <li class="list-group-item" v-for="(todo, index) in contents">
        {{todo.contents_nm}}
        <div class="btn-group pull-right"
             style="font-size: 12px; line-height: 1;">
          <!--<button type="button"
                  class="btn-link dropdown-toggle"
                  data-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false">
            더보기<span class="caret"></span>
          </button>
          <ul class="dropdown-menu">
            <li><a href="#">삭제</a></li>
          </ul>-->
          <button class="btn btn-primary btn-xs" type="button"
                  data-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
          >수정</button>
          <ul class="dropdown-menu">
            <input type="text" class="form-control" v-model="todo.contents_nm">
            <li><a @click="updateContents(todo.contents_id, todo.contents_nm)">완료</a></li>
            <li><a href="#">취소</a></li>
          </ul>
          <button class="btn btn-danger btn-xs" type="button" @click="deleteContents(todo.contents_id)">삭제</button>
        </div>
      </li>

    </ul>
  </div>
</template>

<script>
    var url = decodeURIComponent(location.href);
    url = decodeURIComponent(url);
    var category_id;
    category_id = url.substring( url.indexOf('contents/')+9, url.length );

    var url = 'http://localhost:8080/contents/';
    export default {
        name: 'contents',
        methods: {
            getList() {
                this.$http.get(url+category_id+'/getList')
                    .then((result) => {
                        console.log(result);
                        for(var i = 0; i < result.data.length; i++){
                            if(result.data[i].category_id == category_id){
                                this.contents.push(result.data[i]);
                            }
                        }
                    })
            },
            insertContents(contents_name) {
                if (contents_name != null) {
                    var postdata = new URLSearchParams();
                    postdata.append('contents_name',contents_name);
                    // postdata.append('category_id',category_id);
                    this.$http.defaults.headers.post['Content-Type'] = 'application/json';
                    this.$http.post(url+category_id+'/insert', postdata).then((result) => {
                        location.href=url+category_id;
                    });
                    this.name = null
                } else {
                    alert("내용을 입력하세요.");
                }
            },
            updateContents(contents_id, contents_name) {
                if (contents_name != null) {
                    var postdata = new URLSearchParams();
                    postdata.append('contents_name', contents_name);
                    postdata.append('contents_id', contents_id);
                    this.$http.defaults.headers.post['Content-Type'] = 'application/json';
                    this.$http.post(url+category_id+'/update', postdata).then((result) => {
                    });
                } else {
                    alert("내용을 입력하세요.");
                }
            },
            deleteContents(contents_id) {
                this.$http.delete(url+contents_id+'/delete').then((result) => {
                    location.href=url+category_id;
                });
            }
        },
        data () {
            return {
                contents: []
            }
        },
        mounted() {
            this.getList();
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .list-group-item {
    height: 40px;
  }
</style>
