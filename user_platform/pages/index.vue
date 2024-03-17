<template>
<<<<<<< HEAD
    <div class="bg-gray-200 flex flex-col px-6 py-3">
        <div class="bg-white px-6 py-4 rounded-lg">
            <div class="py-2 font-bold text-3xl">반가워요,<br />살롱 서치입니다.</div>
            <div class="my-3 grid justify-between grid-cols-4 gap-4 text-center">
                <div class="hover:bg-gray-200 rounded-md">예약시간으로 검색하기</div>
                <div class="hover:bg-gray-200 rounded-md">미용 제품 구매하기</div>
                <div class="hover:bg-gray-200 rounded-md" v-for="x in 7" style="height: 60px;">헤어샵</div>

            </div>
        </div>

        <div class="mt-3 text-center">
            <GeoLocation />
        </div>

        <div class="my-3 px-6 py-4 bg-white rounded-lg flex justify-between items-center">
            <div class="text-xs md:text-lg">다른 지역의 미용실을 찾고 계신가요?</div>
            <UButton color="black">위치 변경하기</UButton>
        </div>

        <div class=" py-4 mb-3">
            <div class="font-semibold text-xl px-6">베스트 리뷰 미용실</div>
            <UCarousel v-slot="{ item }" :items="items" class="overflow-hidden">
                <div class="flex-col items-center gap-4 my-5 mr-3 py-2 px-2 rounded-lg bg-white">
                    <img class="rounded-md" :src="item.imgUrl" width="280" draggable="false">
                    <div class="bg-gray-800 text-white py-2 px-3 mt-2 rounded-md">
                        <div class="text-lg">미용실 {{ item.index }}</div>
                        <div>거리 : 3.4km</div>
                        <div>커트 : 13000원</div>
                    </div>
                </div>
            </UCarousel>
        </div>

        <div class="rounded-lg bg-white px-6 py-4 mb-3">
            <UInput size="xl" placeholder="미용실 검색하기.." icon="i-heroicons-magnifying-glass-20-solid"></UInput>
        </div>


        <div class="rounded-lg bg-white px-6 py-4">
            <div class="font-semibold text-xl">내 주변 미용실</div>
            <div v-for="x in 12" class="flex items-center gap-4 my-5 hover:bg-gray-200 py-4 px-4 rounded-md">
                <UAvatar size="2xl"></UAvatar>
                <div>
                    <div>미용실 {{ x }}</div>
                    <div>거리 : 3.4km</div>
                    <div>커트 : 13000원</div>
                </div>
            </div>
        </div>
        
        
    </div>
</template>

<script setup>
const items = [
    {
        index: 1,
        name: 'haeyoom',
        imgUrl: 'https://modo-phinf.pstatic.net/20210122_116/1611280437591fxcIm_JPEG/mosatCIAUP.jpeg?type=a1100',
    },
    {
        index: 1,
        name: 'haeyoom',
        imgUrl: 'https://modo-phinf.pstatic.net/20210122_116/1611280437591fxcIm_JPEG/mosatCIAUP.jpeg?type=a1100',
    },
    {
        index: 1,
        name: 'haeyoom',
        imgUrl: 'https://modo-phinf.pstatic.net/20210122_116/1611280437591fxcIm_JPEG/mosatCIAUP.jpeg?type=a1100',
    },
    {
        index: 1,
        name: 'haeyoom',
        imgUrl: 'https://modo-phinf.pstatic.net/20210122_116/1611280437591fxcIm_JPEG/mosatCIAUP.jpeg?type=a1100',
    },
    {
        index: 1,
        name: 'haeyoom',
        imgUrl: 'https://modo-phinf.pstatic.net/20210122_116/1611280437591fxcIm_JPEG/mosatCIAUP.jpeg?type=a1100',
    },
    {
        index: 1,
        name: 'haeyoom',
        imgUrl: 'https://modo-phinf.pstatic.net/20210122_116/1611280437591fxcIm_JPEG/mosatCIAUP.jpeg?type=a1100',
    },
    {
        index: 1,
        name: 'haeyoom',
        imgUrl: 'https://modo-phinf.pstatic.net/20210122_116/1611280437591fxcIm_JPEG/mosatCIAUP.jpeg?type=a1100',
    },
    {
        index: 1,
        name: 'haeyoom',
        imgUrl: 'https://modo-phinf.pstatic.net/20210122_116/1611280437591fxcIm_JPEG/mosatCIAUP.jpeg?type=a1100',
    },
    {
        index: 1,
        name: 'haeyoom',
        imgUrl: 'https://modo-phinf.pstatic.net/20210122_116/1611280437591fxcIm_JPEG/mosatCIAUP.jpeg?type=a1100',
    },
]
=======
    <div class="flex flex-row justify-center py-52">
        <div class="font-bold text-5xl">USERPLATFORM</div>
    </div>
</template>

<script setup lang="ts">
>>>>>>> main
</script>
