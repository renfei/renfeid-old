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
    init: {
      type: Object,
      default: () => {
        return {}
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
  }),
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
}
</script>
