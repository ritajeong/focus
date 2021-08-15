<template>
  <div class="example-drag">
    <div v-show="$refs.upload && $refs.upload.dropActive" class="drop-active">
      <h3>Drop files to upload</h3>
    </div>
    <template v-if="files.length">
      <v-data-table
        dense
        :headers="headers"
        :items="files"
        item-key="name"
        class="elevation-1"
        hide-default-footer
      >
      </v-data-table>
      <v-btn
        class="ma-2"
        style="padding: 0px"
        color="info"
        @click="uploadStart()"
      >
        <v-icon style="margin-right: 5px">arrow_upward</v-icon>UpLoad Start
      </v-btn>
    </template>
    <template v-else>
      <!-- <FileUpload
        class="btn btn-primary"
        :multiple="true"
        :drop="true"
        :drop-directory="true"
        v-model="files"
        ref="upload"
        @input="onDrop()"
      >
      </FileUpload> -->
      <v-row>
        <v-col cols="12" sm="12" md="12">
          <div class="text-center p-5">
            <h4>Drop files anywhere to upload<br />or</h4>
            <v-btn class="ma-2" style="padding: 0px" color="info">
              <label for="file" style="padding: 0px 8px"
                ><v-icon style="margin-right: 5px">add_circle</v-icon>Select
                File</label
              >
            </v-btn>
          </div>
        </v-col>
      </v-row>
    </template>
  </div>
</template>
<script>
// import FileUpload from 'vue-upload-component';
// import axios from 'axios';

export default {
  components: {
    // FileUpload,
  },
  data: () => ({
    files: [],
    headers: [
      { text: 'name', value: 'name' },
      { text: 'size', value: 'size' },
    ],
    imgfile: '',
  }),
  methods: {
    fileSelect() {
      console.log(this.$refs);
      this.imgfile = this.$refs.img.files[0];
    },
    onDrop(item) {
      console.log(item);
    },
    // uploadStart() {
    //   const formData = new FormData();

    //   formData.append('user_id', '2');
    //   formData.append('room_id', '1');
    //   formData.append('size', '0');
    //   console.log(formData);
    //   for (let i = 0; i < this.files.length; i++) {
    //     formData.append('files', this.files[i]);
    //     console.log(this.files[i]);
    //   }
    //   axios
    //     .post('http://localhost:8446/board/down', formData, {
    //       headers: {
    //         'Content-Type': 'multipart/form-data',
    //       },
    //     })
    //     .then(res => {
    //       console.log('111111111');
    //       console.log(res);
    //     })
    //     .catch(e => {
    //       console.log('2222222222');
    //       console.log(e);
    //     });
    // downloadFile(formData)
    //   .then(({ status }) => {
    //     console.log(status);
    //     if (status != 200) {
    //       this.$alertify.error('방 정보 수정을 실패했습니다.');
    //       return;
    //     } else {
    //       this.$alertify.success('방 정보가 수정됐습니다.');
    //       this.$router.push('/dashboard');
    //     }
    //   })
    //   .catch(() => {
    //     this.$alertify.error('error! catch');
    //   });
    // },
  },
};
</script>
<style>
.example-drag .drop-active {
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  position: fixed;
  z-index: 9999;
  opacity: 0.6;
  text-align: center;
  width: 100%;
  background: #000;
}
.example-drag .drop-active h3 {
  margin: -0.5em 0 0;
  position: absolute;
  top: 50%;
  left: 0;
  right: 0;
  -webkit-transform: translateY(-50%);
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
  font-size: 40px;
  color: #fff;
  padding: 0;
}
</style>
