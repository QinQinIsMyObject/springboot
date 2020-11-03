new Vue({
    el: '#app',
    data() {
        return {
            keywords: '',
            goodsList: {},
            // pageInfo: {
            //     hasNextPage: false,
            //     hasPreviousPage: false,
            //     nextPage: 0,
            //     prePage: 0,
            //     pageNum: 0,
            //     pages: 0,
            //     total: 0
            // }
        }
    },
    methods: {
        goodsPage(pageNum) {
            if (this.keywords === '') {
                http.get('/api/goods/goodsList', { pageNum }).then(resp => {
                    this.goodsList = resp.data.data.goodsList
                })
            } else {
                http.get('/api/goods/findGoodsByKeywords', { keywords: this.keywords, pageNum}).then(resp => {
                    this.goodsList = resp.data.data.findByKeywords
                })
            }
            
        },
        goodsDetails(gid) {
            location.href = './product-details.html?gid='+gid
        },
        searchByKeywords() {
            http.get('/api/goods/findGoodsByKeywords', { keywords: this.keywords }).then(resp => {
                this.goodsList = resp.data.data.findByKeywords
            })
        }
    },
    created() {
        http.get('/api/goods/goodsList').then(resp => {
            this.goodsList = resp.data.data.goodsList
        })
    },
    computed: {
        pages() {
            return new Array(this.goodsList.pages)
        }
    },
})