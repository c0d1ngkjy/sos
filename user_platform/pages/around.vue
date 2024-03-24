<template>
    <div class="bg-gray-200 py-3 px-6">
        <div class="flex gap-2 items-center">
            <div class="px-6 py-4 bg-white rounded-lg flex justify-between items-center flex-grow">
                <div class="bg-gray-200 rounded-md py-1 px-4 cursor-pointer" @click="handleChangeLocation()">
                    <div>{{ strLoc.road_address?.address_name }}</div>
                </div>
                <UButton color="black">위치 변경하기</UButton>
            </div>
    
            <div class="px-6 py-4 bg-white rounded-lg">
                <USelect v-model="searchFilter" variant="none" size="sm" :options="searchBy" />
            </div>
        </div>

        <div class="mt-3 px-6 py-4 bg-white rounded-lg flex items-center">
            <div>예약 시간 설정하기</div>
        </div>

        <div class="rounded-lg bg-white px-6 py-4 my-3">
            <div class="font-semibold text-xl">내 주변 미용실</div>
            <div v-for="x in 12" class="flex items-center gap-4 my-5 hover:bg-gray-200 py-4 px-4 rounded-md">
                <USkeleton class="h-[120px] w-[120px]"></USkeleton>
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
import { ref } from 'vue';

const searchBy = ["미용실", "디자이너"];
const searchFilter = ref(searchBy[0])

const location = ref(null);
const strLoc = ref({})

async function cordToAddress(lon, lat) {
    $fetch(`https://dapi.kakao.com/v2/local/geo/coord2address.json?x=${lon}&y=${lat}`, {
        method: 'GET',
        headers: {
            'Authorization': `KakaoAK ${useRuntimeConfig().public.kakaoRestApiKey}`
        },
    }).then((res) => {
        console.log(res.documents)
        strLoc.value = res.documents[0]
    })
};

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            position => {
                location.value = {
                    latitude: position.coords.latitude,
                    longitude: position.coords.longitude
                };

                cordToAddress(location.value.longitude, location.value.latitude)
            },
            error => {
                console.error('Error getting location:', error.message);
            }
        );
    } else {
        console.error('Geolocation is not supported by this browser.');
    }
};

onMounted(getLocation);

function handleChangeLocation() {
    console.log("chage loc")
}
</script>