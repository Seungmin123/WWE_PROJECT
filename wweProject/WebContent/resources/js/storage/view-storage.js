function downloadFile(ofname,rfname,savePath) {
    let params = {
        'ofname' : ofname,
        'rfname' : rfname,
        'savePath' : savePath
    };
    location.href = '${context}' + "/storage/download?" + urlEncodeForm(params);
}

function deleteFile(idx,rfname,savePath,isTeam) {
    let params = {
        'fileIdx' : idx,
        'rfname' : rfname,
        'savePath' : savePath,
        'isTeam' : isTeam
    };
    location.href = '${context}' + "/storage/delete?" + urlEncodeForm(params);
}