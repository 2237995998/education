<template>
    <div>
        <el-button type="primary" round @click="open()">开启</el-button>
        <el-button type="danger" round @click="close()">关闭</el-button>
    </div>
</template>

<script>
    export default {
        name: "open-close-choose",
        methods: {
            open(){
                this.$axios.get("/course/open",
                    {
                        headers: {
                            "Authorization": this.$store.getters.getToken
                        }
                    }
                ).then(response=>{
                    this.$message({
                        message: response.data.message,
                        type: "success"
                    })
                })
            },
            close(){
                this.$axios.get("/course/close",
                    {
                        headers: {
                            "Authorization": this.$store.getters.getToken
                        }
                    }
                ).then(response=>{
                    this.$message({
                        message: response.data.message,
                        type: "success"
                    })
                })
            }
        }
    }
</script>

<style scoped>

</style>