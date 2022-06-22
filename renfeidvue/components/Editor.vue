<template>
  <div>
    <editor
        api-key="[your api key]"
        selector="textarea"
        :disabled="disabled"
        :init="init"
        :initial-value="initValue"
        :inline="inline"
        :output-format="outputFormat"
        :plugins="plugins"
        :toolbar="toolbar"
        :contextmenu="contextmenu"
        :key="tinymceFlag"
        :height="height"
        v-model="content"
        images_upload_url="http://localhost:9595/_/api/core/system/upload"
    />
  </div>
</template>

<script>
import Editor from '@tinymce/tinymce-vue'

export default {
  name: 'Editor',
  props: {
    editorContent: {
      type: String,
      default: () => {
        return ""
      }
    },
    disabled: {
      type: Boolean,
      default: () => {
        return false
      }
    },
    height: {
      type: Number,
      default: () => {
        return 200
      }
    },
    initValue: {
      type: String,
      default: () => {
        return ""
      }
    },
    inline: {
      type: Boolean,
      default: () => {
        return false
      }
    },
    outputFormat: {
      type: String,
      default: () => {
        return "html"
      }
    },
    plugins: {
      type: String,
      default: () => {
        return "preview importcss searchreplace autolink autosave save directionality code visualblocks visualchars fullscreen image link media template codesample table charmap pagebreak nonbreaking anchor insertdatetime advlist lists wordcount help charmap quickbars emoticons"
      }
    },
    toolbar: {
      type: String,
      default: () => {
        return `undo redo | bold italic underline strikethrough
        | formatselect fontfamily fontsize blocks | alignleft aligncenter alignright alignjustify
        | outdent indent |  numlist bullist | forecolor backcolor removeformat
        | pagebreak | charmap emoticons | fullscreen  preview save print
        | insertfile image media template link anchor codesample | ltr rtl`
      }
    },
    contextmenu: {
      type: String,
      default: () => {
        return "link image table"
      }
    },
  },
  components: {
    'editor': Editor
  },
  data: () => ({
    apiKey: "",
    tinymceFlag: 1,
    content: '',
    init: {},
  }),
  created() {
    this.init = {
      language: "zh_CN",
      height: 400,
      statusbar: false, //最下方的元素路径和字数统计那一栏是否显示
      elementpath: false, //隐藏底栏的元素路径
      plugins: this.plugins,
      toolbar: this.toolbar,
      branding: false,
      menubar: true,
      table_default_styles: {
        width: "100%",
        borderCollapse: "collapse",
      },
      paste_data_images: true, //图片是否可粘贴
      image_description: true,
      image_title: false, // 是否开启图片标题设置的选择，这里设置否
      // 此处为图片上传处理函数，这个直接用了base64的图片形式上传图片，
      images_upload_handler: (blobInfo, success, failure) => {
        this.images_upload_handler(blobInfo, success, failure)
      },
    }
  },
  mounted() {
    if (this.editorContent) {
      this.content = this.editorContent
    }
  },
  watch: {
    content: {
      handler(newValue, oldValue) {
        this.$emit('input', newValue)
      }
    },
    editorContent: {
      handler() {
        this.content = this.editorContent
      },
      immediate: true
    }
  },
  activated() {
    this.tinymceFlag++
  },
  methods: {
    images_upload_handler: async function (blobInfo, success, failure) {
      if (blobInfo.blob().size > this.$renfeid.uploadMaxSize) {
        failure("您上传的文件超过允许的最大尺寸：" + this.$renfeid.uploadMaxSizeText)
      } else {
        var form = new FormData();
        form.append('file', blobInfo.blob(), blobInfo.filename());
        const [uploadObjectVo] = await Promise.all([
          this.$api.uploadObject({}, form, {})
        ])
        success(uploadObjectVo.data.data.location)
      }
    },
  },
}
</script>
