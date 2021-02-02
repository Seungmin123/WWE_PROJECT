function downloadFile(ofname,rfname,savePath) {
    let params = {
        'ofname' : ofname,
        'rfname' : rfname,
        'savePath' : savePath
    };
    location.href = '${context}' + "/storage/download?" + urlEncodeForm(params);
}

function deleteFile(ofname,rfname,savePath) {
    let params = {
        'ofname' : ofname,
        'rfname' : rfname,
        'savePath' : savePath
    };
    location.href = '${context}' + "/storage/delete?" + urlEncodeForm(params);
}