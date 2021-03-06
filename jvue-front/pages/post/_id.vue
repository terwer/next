<template>
  <el-container>
    <el-main>
      <el-row>
        <el-col :xs="0" :md="2">&nbsp;</el-col>
        <el-col :xs="24" :md="20">
          <el-main>
            <el-container>
              <el-main>
                <el-container>
                  <el-header>
                    <HeaderTime />
                  </el-header>
                  <el-header>
                    <Header />
                  </el-header>
                  <el-main>
                    <div id="post" class="post-default">
                      <!-- 导航 -->
                      <el-breadcrumb separator="/">
                        <el-breadcrumb-item
                          v-for="item in items"
                          :key="item.text"
                          :to="item.to"
                        >
                          {{ item.text }}
                        </el-breadcrumb-item>
                      </el-breadcrumb>

                      <!-- 文章标题 -->
                      <div id="postTitle">
                        <nuxt-link
                          :to="
                            postObj.name === ''
                              ? '/post/' + postObj.id + '.html'
                              : '/post/' + postObj.name + '.html'
                          "
                        >
                          <h1>
                            {{ postObj.title }}
                          </h1>
                        </nuxt-link>
                      </div>

                      <!-- 文章详情 -->
                      <div
                        id="postContent"
                        v-highlight
                        v-html="postObj.content"
                      ></div>

                      <h1 v-if="errorMessage !== ''" class="error-message">
                        {{ errorMessage }}
                      </h1>

                      <div class="copy">
                        <p>作者：Terwer</p>
                        <p>首发：远方的灯塔</p>
                        <p>
                          原创内容，转载请注明出处！
                        </p>
                      </div>
                    </div>
                  </el-main>
                </el-container>
              </el-main>
            </el-container>
          </el-main>
        </el-col>
        <el-col :xs="0" :md="2">&nbsp;</el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-footer>
            <Footer :site-config="siteConfigObj" />
            <FriendLink />
          </el-footer>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
/* eslint no-undef: 0 */ // --> OFF
/* eslint nuxt/no-globals-in-created: 0 */ // --> OFF
/* eslint vue/no-v-html: 0 */ // --> OFF
/* eslint no-useless-escape: 0 */ // --> OFF
import { getLogger } from "../../util/logger";
import HeaderTime from "../../components/themes/default/HeaderTime";
import Header from "../../components/themes/default/Header";
import Footer from "../../components/themes/default/Footer";
import FriendLink from "../../components/themes/default/FriendLink";
import { inBrowser } from "../../util/dom";

const logger = getLogger("pages/post");

export default {
  components: { HeaderTime, Header, Footer, FriendLink },
  data() {
    return {
      id: this.$route.params.id.replace(/\.[^/.]+$/, ""),
      items: [
        {
          text: "首页",
          to: { path: "/" }
        },
        {
          text: "文章"
        }
      ]
    };
  },
  async asyncData(context) {
    const siteConfigResult = await context.$axios.$post("/site/config/list");
    const siteConfigObj =
      siteConfigResult.status === 1 ? siteConfigResult.data : {};

    const id = context.route.params.id.replace(/\.[^/.]+$/, "");
    const postParams = {
      postSlug: id
    };
    const postResult = await context.$axios.$post(
      "/blog/post/detail",
      postParams
    );
    let postObj = {};
    let errorMessage = "";
    if (postResult.status === 1) {
      postObj = postResult.data;
    } else {
      errorMessage = postResult.msg;
    }

    logger.info("fetch siteConfig and post finish");

    return { siteConfigObj, postObj, errorMessage };
  },
  head() {
    return {
      title:
        this.errorMessage === ""
          ? this.postObj.title + " - " + this.siteConfigObj.webname
          : this.errorMessage,
      meta: [
        {
          name: "keywords",
          content: this.siteConfigObj.keywords
        },
        {
          hid: "description",
          name: "description",
          content: this.siteConfigObj.description
        }
      ],
      script: [
        {
          src:
            "https://cdn.staticfile.org/mathjax/2.7.5/MathJax.js?config=TeX-AMS-MML_HTMLorMML"
        }
      ]
    };
  },
  created() {
    if (inBrowser && window.MathJax) {
      // 高亮数学公式
      MathJax.Hub.Config({
        tex2jax: {
          inlineMath: [["$", "$"], ["\(", "\)"]]
        }
      });

      this.$nextTick(function() {
        MathJax.Hub.Queue(["Typeset", MathJax.Hub]);
      });
      logger.info("MathJax hilight success");
    }
  }
};
</script>

<style lang="scss">
@import "../common.css";
@import "../default.css";
@import "../../plugins/lib/vue-hljs/vs.css";
</style>

<style lang="scss">
.post-default {
  margin: 20px;
}
.post-default #postTitle {
  a {
    color: #000;
    line-height: 1.5;
    text-decoration: none;
  }
  a:hover {
    color: red;
  }
  h1 {
    border-bottom: 1px solid #ddd;
    font-size: 14px;
    font-weight: bold;
    margin: 20px 0 10px;
    padding-bottom: 5px;
  }
}

.post-default #postContent {
  h1 {
    font-size: 28px;
    font-weight: bold;
    line-height: 1.5;
    margin: 10px 0;
  }
  h2 {
    font-size: 21px;
    font-weight: bold;
    line-height: 1.5;
    margin: 10px 0;
  }
  p {
    // 图片自适应
    img {
      max-width: 100% !important;
    }
  }
}
.error-message {
  font-size: 48px;
  text-align: center;
  color: #000000;
  line-height: 1.6;
}

/* 顺序列表 */
.post-default {
  ul,
  ol {
    li {
      code {
        font-size: 16px;
      }
      p {
        code {
          font-size: 16px;
        }
      }
    }
  }

  blockquote {
    color: black;
    background: #f5f5f5 !important;
    padding: 0 10px;
    margin: 10px 0;
    border: 1px solid #ccc !important;
    border-radius: 3px !important;
    code {
      font-size: 16px;
    }
    p {
      code {
        font-size: 16px;
      }
    }
  }
}
</style>
